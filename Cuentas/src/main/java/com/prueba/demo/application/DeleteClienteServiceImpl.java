/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.demo.application;

import com.prueba.demo.domain.ports.in.DeleteClienteService;
import com.prueba.demo.domain.ports.out.RepositoryCliente;

/**
 *
 * @author chris
 */
public class DeleteClienteServiceImpl implements DeleteClienteService {

    private final RepositoryCliente repository;

    public DeleteClienteServiceImpl(RepositoryCliente repository) {
        this.repository = repository;
    }

    @Override
    public void eliminarCliente(String clienteId) {
        repository.eliminar(clienteId);
    }

}
