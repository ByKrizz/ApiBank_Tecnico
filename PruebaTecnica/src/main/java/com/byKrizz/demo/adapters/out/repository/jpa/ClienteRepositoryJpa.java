/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cliente.adapters.out.repository.jpa;

import com.byKrizz.cliente.adapters.out.repository.entidad.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author chris
 */
public interface ClienteRepositoryJpa  extends JpaRepository<ClienteEntity, String> {
    
}
