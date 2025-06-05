/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.adapter.out.repository.mapper;

import com.byKrizz.cuentas.domain.model.Cuenta;
import com.byKrizz.cuentas.domain.model.Movimiento;
import com.byKrizz.cuentas.domain.model.TipoMovimiento;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.entidad.CuentaEntity;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.entidad.MovimientoEntity;

/**
 *
 * @author chris
 */
public class MovimientoMapper {

    public static Movimiento toDomain(MovimientoEntity entity) {
        if (entity == null) {
            return null;
        }

        Movimiento m = new Movimiento();
        m.setNumeroCuenta(entity.getCuenta() != null ? entity.getCuenta().getNumeroCuenta() : null);
        m.setTipoCuenta(entity.getCuenta() != null ? entity.getCuenta().getTipo() : null);
        m.setSaldoInicial(entity.getCuenta() != null ? entity.getCuenta().getSaldoInicial() : null);
        m.setEstadoCuenta(entity.getCuenta() != null ? entity.getCuenta().isEstado() : false);
        m.setTipoMovimiento(entity.getTipo() != null ? entity.getTipo().name() : null);
        m.setValorMovimiento(entity.getValor());
        m.setSaldoDisponible(entity.getSaldoDisponible());
        m.setFechaMovimiento(entity.getFecha());
        return m;
    }

    // De dominio a entity
    public static MovimientoEntity toEntity(Movimiento m, Cuenta cuenta) {
        if (m == null) {
            return null;
        }

        MovimientoEntity entity = new MovimientoEntity();

        // Si tienes ID en Movimiento lo asignas aquí (añade en Movimiento si hace falta)
        // entity.setId(m.getId()); // si tienes el campo id en dominio
        entity.setCuenta(CuentaMapper.toEntity(cuenta));
        entity.setTipo(m.getTipoMovimiento() != null ? TipoMovimiento.valueOf(m.getTipoMovimiento()) : null);
        entity.setValor(m.getValorMovimiento());
        entity.setSaldoDisponible(cuenta.getSaldoInicial());
        entity.setFecha(m.getFechaMovimiento());
        entity.setDescripcion("Movimiento generado"); // o usa algún campo para descripción
        return entity;
    }

    public static MovimientoEntity toEntity(Movimiento movimiento, CuentaEntity cuentaEntity) {
        MovimientoEntity entity = new MovimientoEntity();
        entity.setCuenta(cuentaEntity); // ¡IMPORTANTE!
        entity.setDescripcion(movimiento.getDescripcion());
        entity.setFecha(movimiento.getFechaMovimiento());
        entity.setTipo(movimiento.getTipoMovimiento() != null ? TipoMovimiento.valueOf(movimiento.getTipoMovimiento()) : null);
        entity.setValor(movimiento.getValorMovimiento());
        entity.setSaldoDisponible(cuentaEntity.getSaldoInicial());
        return entity;
    }
}
