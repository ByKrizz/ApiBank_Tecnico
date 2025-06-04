/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.adapter.out.repository.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author chris
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_cuentas")
public class CuentaEntity {

    @Id
    private String numeroCuenta;

    private String tipo;

    private BigDecimal saldoInicial;

    private boolean estado;

    @Column(name = "clienteId")
    private String clienteId;
}
