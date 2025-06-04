/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.domain.model;

import java.math.BigDecimal;
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

    private Long id;
    private String numeroCuenta;
    private TipoMovimiento tipo; // DEBITO o CREDITO
    private BigDecimal valor;
    private LocalDateTime fecha;
}
