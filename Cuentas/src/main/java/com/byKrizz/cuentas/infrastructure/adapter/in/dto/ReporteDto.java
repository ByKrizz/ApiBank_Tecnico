/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.adapter.in.dto;

import java.math.BigDecimal;

/**
 *
 * @author chris
 */
public class ReporteDto {
 private String fecha;
    private String clienteNombre;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private boolean estadoCuenta;
    private BigDecimal valorMovimiento;
    private BigDecimal saldoDisponible;

    public ReporteDto(String fecha, String clienteNombre, String numeroCuenta,
                      String tipoCuenta, BigDecimal saldoInicial, boolean estadoCuenta,
                      BigDecimal valorMovimiento, BigDecimal saldoDisponible) {
        this.fecha = fecha;
        this.clienteNombre = clienteNombre;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estadoCuenta = estadoCuenta;
        this.valorMovimiento = valorMovimiento;
        this.saldoDisponible = saldoDisponible;
    }
}
