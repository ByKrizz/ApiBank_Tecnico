/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cliente.application;

import com.byKrizz.cliente.domain.model.Cliente;
import com.byKrizz.cliente.domain.ports.in.FindClienteService;
import com.byKrizz.cliente.domain.ports.out.RepositoryCliente;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author chris
 */
public class FindClienteServiceImpl implements FindClienteService {

    private final RepositoryCliente repository;

    public FindClienteServiceImpl(RepositoryCliente repository) {
        this.repository = repository;
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return repository.buscarTodos();
    }

    @Override
    public Optional<Cliente> obtenerPorId(String clienteId) {
        return repository.buscarPorId(clienteId);
    }
}
