/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.domain.ports.in;

import java.math.BigDecimal;

/**
 *
 * @author chris
 */
public interface MovimientoService {
    void debitar(String numeroCuenta, BigDecimal monto, String descripcion);
    void acreditar(String numeroCuenta, BigDecimal monto, String descripcion);
}
