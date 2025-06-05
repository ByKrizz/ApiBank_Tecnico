/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.adapter.out.repository.entidad;

import com.byKrizz.cuentas.domain.model.Cuenta;
import com.byKrizz.cuentas.domain.model.TipoMovimiento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author chris
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_movimiento")
public class MovimientoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CuentaEntity  cuenta;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipo;

    private BigDecimal valor;

    private BigDecimal saldoDisponible;

    private String descripcion;

    private LocalDateTime fecha;
}
