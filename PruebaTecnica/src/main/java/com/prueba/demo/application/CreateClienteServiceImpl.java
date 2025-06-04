/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.demo.application;

import com.prueba.demo.domain.model.Cliente;
import com.prueba.demo.domain.ports.in.CreateClienteService;
import com.prueba.demo.domain.ports.out.RepositoryCliente;
import org.springframework.scheduling.annotation.Async;

/**
 *
 * @author chris
 */
public class CreateClienteServiceImpl implements CreateClienteService {

    private final RepositoryCliente repository;

    public CreateClienteServiceImpl(RepositoryCliente repository) {
        this.repository = repository;
    }

    @Override
    @Async
    public Cliente crearCliente(Cliente cliente) {
        return repository.guardar(cliente);
    }
}
