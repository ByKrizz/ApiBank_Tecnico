/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.domain.service;

import com.byKrizz.cuentas.domain.model.Cliente;
import com.byKrizz.cuentas.domain.model.Cuenta;
import com.byKrizz.cuentas.domain.model.Movimiento;
import com.byKrizz.cuentas.domain.ports.in.MovimientoService;
import com.byKrizz.cuentas.domain.ports.out.ClienteRemoteService;
import com.byKrizz.cuentas.domain.ports.out.MovimientoRepository;
import com.byKrizz.cuentas.infrastructure.adapter.in.dto.MovimientoDto;
import com.byKrizz.cuentas.infrastructure.adapter.in.dto.ReporteDto;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.entidad.CuentaEntity;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.entidad.MovimientoEntity;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.jpa.CuentaRepositoryJpa;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.jpa.MovimientoRepositoryJpa;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.mapper.CuentaMapper;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.mapper.MovimientoMapper;
import com.byKrizz.cuentas.shared.exeption.FondosInsuficientesException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
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
    private final ClienteRemoteService clienteRemoteService;

    public MovimientosCuentaImpl(MovimientoRepositoryJpa movimientoRepositoryJpa, CuentaRepositoryJpa cuentaRepositoryJpa, ClienteRemoteService clienteRemoteService) {
        this.movimientoRepositoryJpa = movimientoRepositoryJpa;
        this.cuentaRepositoryJpa = cuentaRepositoryJpa;
        this.clienteRemoteService = clienteRemoteService;
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

    @Override
    public List<ReporteDto> findByCuentaClienteIdAndFechaMovimientoBetween(String clienteId, LocalDateTime desde, LocalDateTime hasta) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<MovimientoEntity> movimientos = movimientoRepositoryJpa
                .findByCuentaClienteIdAndFechaBetween(clienteId, desde, hasta);

        Cliente cliente = clienteRemoteService.obtenerClientePorId(clienteId)
                .filter(Cliente::isEstado)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no válido o inactivo"));

        String clienteNombre = cliente.getNombres() != null ? cliente.getNombres() : "Cliente desconocido";

        return movimientos.stream().map(m -> {
            CuentaEntity cuenta = m.getCuenta();
            String fechaFormateada = m.getFecha() != null ? m.getFecha().format(formatter) : "";

            return new ReporteDto(
                    fechaFormateada,
                    clienteNombre,
                    cuenta.getNumeroCuenta(),
                    cuenta.getTipo(),
                    cuenta.getSaldoInicial(),
                    cuenta.isEstado(),
                    m.getValor(),
                    m.getSaldoDisponible()
            );
        }).collect(Collectors.toList());
    }
}
