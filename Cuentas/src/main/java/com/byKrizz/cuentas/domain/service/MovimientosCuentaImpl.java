/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.domain.service;

import com.byKrizz.cuentas.domain.model.Cuenta;
import com.byKrizz.cuentas.domain.model.Movimiento;
import com.byKrizz.cuentas.domain.ports.in.MovimientoService;
import com.byKrizz.cuentas.domain.ports.out.MovimientoRepository;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.entidad.CuentaEntity;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.entidad.MovimientoEntity;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.jpa.CuentaRepositoryJpa;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.jpa.MovimientoRepositoryJpa;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.mapper.CuentaMapper;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.mapper.MovimientoMapper;
import com.byKrizz.cuentas.shared.exeption.FondosInsuficientesException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
public class MovimientosCuentaImpl implements MovimientoService {

    private final MovimientoRepositoryJpa movimientoRepositoryJpa;
    private final CuentaRepositoryJpa cuentaRepositoryJpa;

    public MovimientosCuentaImpl(MovimientoRepositoryJpa movimientoRepositoryJpa, CuentaRepositoryJpa cuentaRepositoryJpa) {
        this.movimientoRepositoryJpa = movimientoRepositoryJpa;
        this.cuentaRepositoryJpa = cuentaRepositoryJpa;
    }

    public Movimiento guardar(Movimiento movimiento) {
        CuentaEntity cuentaEntity = cuentaRepositoryJpa.findByNumeroCuenta(movimiento.getNumeroCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        MovimientoEntity entity = MovimientoMapper.toEntity(movimiento, cuentaEntity);
        MovimientoEntity saved = movimientoRepositoryJpa.save(entity);
        return MovimientoMapper.toDomain(saved);
    }

    public List<Movimiento> obtenerPorNumeroCuenta(String numeroCuenta) {
        return movimientoRepositoryJpa.findByCuentaNumeroCuenta(numeroCuenta)
                .stream()
                .map(MovimientoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void debitar(String numeroCuenta, BigDecimal monto, String descripcion) {

        CuentaEntity cuenta = cuentaRepositoryJpa.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        if (cuenta.getSaldoInicial().compareTo(monto) < 0) {
            throw new FondosInsuficientesException("Fondos insuficientes en la cuenta");
        }

        cuenta.setSaldoInicial(cuenta.getSaldoInicial().subtract(monto));
        cuentaRepositoryJpa.save(cuenta);

        Movimiento movimiento = new Movimiento();
        movimiento.setNumeroCuenta(numeroCuenta);
        movimiento.setTipoMovimiento("DEBITO");
        movimiento.setValorMovimiento(monto.negate()); // monto negativo para débito
        movimiento.setDescripcion(descripcion);
        movimiento.setFechaMovimiento(LocalDateTime.now());

        guardar(movimiento);
    }

    @Override
    public void acreditar(String numeroCuenta, BigDecimal monto, String descripcion) {

        CuentaEntity cuenta = cuentaRepositoryJpa.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

//        cuenta.setSaldoInicial(cuenta.getSaldoInicial().add(monto));
        BigDecimal saldoActual = cuenta.getSaldoInicial() != null ? cuenta.getSaldoInicial() : BigDecimal.ZERO;
        cuenta.setSaldoInicial(saldoActual.add(monto));
        cuentaRepositoryJpa.save(cuenta);

        Movimiento movimiento = new Movimiento();
        movimiento.setNumeroCuenta(numeroCuenta);
        movimiento.setTipoMovimiento("CREDITO");
        movimiento.setValorMovimiento(monto.negate()); // monto negativo para débito
        movimiento.setDescripcion(descripcion);
        movimiento.setFechaMovimiento(LocalDateTime.now());

        guardar(movimiento);
    }
}
