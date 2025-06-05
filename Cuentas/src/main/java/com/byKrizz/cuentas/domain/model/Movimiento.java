/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author chris
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movimiento {

    private String numeroCuenta;
    private String tipoCuenta;         // Ej: AHORROS, CORRIENTE
    private BigDecimal saldoInicial;
    private boolean estadoCuenta;       // Ej: ACTIVA, INACTIVA
    private String tipoMovimiento;     // CREDITO o DEBITO
    private BigDecimal valorMovimiento;

    private BigDecimal saldoDisponible;

    private String descripcion;
    private LocalDateTime  fechaMovimiento;
}
