/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cliente.application;

import com.byKrizz.cliente.domain.model.Cliente;
import com.byKrizz.cliente.domain.ports.in.CreateClienteService;
import com.byKrizz.cliente.domain.ports.out.RepositoryCliente;
import java.util.concurrent.CompletableFuture;
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
    public CompletableFuture<Cliente> crearCliente(Cliente cliente) {
        Cliente guardado = repository.guardar(cliente);
        return CompletableFuture.completedFuture(guardado);
    }
//    public Cliente crearCliente(Cliente cliente) {
//        return repository.guardar(cliente);
//    }
   
}