/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.demo.application;

import com.prueba.demo.domain.model.Cliente;
import com.prueba.demo.domain.ports.in.UpdateClienteService;
import com.prueba.demo.domain.ports.out.RepositoryCliente;

/**
 *
 * @author chris
 */
public class UpdateClienteServiceImpl implements UpdateClienteService {
    
    private final RepositoryCliente repository;
    
    public UpdateClienteServiceImpl(RepositoryCliente repository) {
        this.repository = repository;
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return repository.guardar(cliente);
    }
    
}
