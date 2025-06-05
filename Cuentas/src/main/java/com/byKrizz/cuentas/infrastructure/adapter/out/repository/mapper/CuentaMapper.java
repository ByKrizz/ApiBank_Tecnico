/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.adapter.out.repository.mapper;

import com.byKrizz.cuentas.domain.model.Cliente;
import com.byKrizz.cuentas.domain.model.Cuenta;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.entidad.CuentaEntity;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 *
 * @author chris
 */
@Component
public class CuentaMapper {

    public static CuentaEntity toEntity(Cuenta cuenta) {
        if (cuenta == null) {
            return null;
        }

        CuentaEntity entity = new CuentaEntity();
        entity.setNumeroCuenta(cuenta.getNumeroCuenta());
        entity.setTipo(cuenta.getTipo());
        entity.setSaldoInicial(cuenta.getSaldoInicial());
        entity.setEstado(cuenta.isEstado());
        return entity;
    }

//    public Cuenta toDomain(CuentaEntity entity) {
//        Cliente cliente = new Cliente();
//        cliente.setClienteId(entity.getClienteId());
//
//        Cuenta cuenta = new Cuenta();
//        cuenta.setNumeroCuenta(entity.getNumeroCuenta());
//        cuenta.setTipo(entity.getTipo());
//        cuenta.setSaldoInicial(entity.getSaldoInicial());
//        cuenta.setEstado(entity.isEstado());
//        cuenta.setCliente(cliente);
//        return cuenta;
//    }
    public static Cuenta toDomain(CuentaEntity entity) {
        if (entity == null) {
            return null;
        }
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(entity.getNumeroCuenta());
        cuenta.setTipo(entity.getTipo());
        cuenta.setSaldoInicial(entity.getSaldoInicial());
        cuenta.setEstado(entity.isEstado());

        // Crear un cliente nuevo y asignar ID
        Cliente cliente = new Cliente();
        cliente.setClienteId(entity.getClienteId());
        cuenta.setCliente(cliente);

        return cuenta;
    }
}
