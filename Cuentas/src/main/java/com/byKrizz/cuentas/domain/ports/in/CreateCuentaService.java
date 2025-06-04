/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.domain.ports.in;

import com.byKrizz.cuentas.domain.model.Cuenta;

/**
 *
 * @author chris
 */
public interface CreateCuentaService {
    Cuenta crearCuenta(Cuenta cuenta);
}