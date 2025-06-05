/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.adapter.out.repository.jpa;

import com.byKrizz.cuentas.domain.model.Movimiento;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.entidad.MovimientoEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author chris
 */
public interface MovimientoRepositoryJpa extends JpaRepository<MovimientoEntity, Long> {
     List<MovimientoEntity> findByCuentaNumeroCuenta(String numeroCuenta);

     List<MovimientoEntity> findByCuentaClienteIdAndFechaBetween(String clienteId, LocalDateTime desde, LocalDateTime hasta);

}
