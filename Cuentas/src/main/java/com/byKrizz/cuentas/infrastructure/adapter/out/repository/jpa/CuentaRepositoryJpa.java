/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.adapter.out.repository.jpa;

import com.byKrizz.cuentas.infrastructure.adapter.out.repository.entidad.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author chris
 */
public interface CuentaRepositoryJpa  extends JpaRepository<CuentaEntity, String> {
    
}
