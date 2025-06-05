/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.domain.ports.out;

import com.byKrizz.cuentas.domain.model.Movimiento;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.entidad.MovimientoEntity;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author chris
 */
public interface MovimientoRepository {

    Movimiento guardar(Movimiento movimiento);

    List<Movimiento> obtenerPorNumeroCuenta(String numeroCuenta);

    List<Movimiento> findByCuentaClienteIdAndFechaMovimientoBetween(String clienteId, LocalDateTime desde, LocalDateTime hasta);

}
