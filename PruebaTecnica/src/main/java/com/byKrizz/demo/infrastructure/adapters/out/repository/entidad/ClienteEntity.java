/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.demo.infrastructure.adapters.out.repository.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author chris
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_clientes")
public class ClienteEntity {
    @Id
    private String clienteId;
    private String identificacion;
    private String nombres;
    private String direccion;
    private String telefono;
    private String contrasena;
    private boolean estado;

    
}
