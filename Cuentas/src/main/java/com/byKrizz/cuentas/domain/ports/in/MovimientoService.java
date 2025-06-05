/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.domain.ports.in;

import com.byKrizz.cuentas.domain.model.Movimiento;
import com.byKrizz.cuentas.infrastructure.adapter.in.dto.MovimientoDto;
import com.byKrizz.cuentas.infrastructure.adapter.in.dto.ReporteDto;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.entidad.MovimientoEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author chris
 */
public interface MovimientoService {

    void debitar(String numeroCuenta, BigDecimal monto, String descripcion);

    void acreditar(String numeroCuenta, BigDecimal monto, String descripcion);

    List<ReporteDto> findByCuentaClienteIdAndFechaMovimientoBetween(String clienteId, LocalDateTime desde, LocalDateTime hasta);

}
