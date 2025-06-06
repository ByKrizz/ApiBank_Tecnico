/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.demo.infrastructure.adapters.out.repository;

import com.byKrizz.demo.infrastructure.adapters.out.repository.entidad.ClienteEntity;
import com.byKrizz.demo.infrastructure.adapters.out.repository.jpa.ClienteRepositoryJpa;
import com.byKrizz.demo.infrastructure.adapters.out.repository.mapper.ClienteMapper;
import com.byKrizz.cliente.domain.model.Cliente;
import com.byKrizz.cliente.domain.ports.out.RepositoryCliente;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chris
 */
@Repository
public class RepositoryClienteImpl implements RepositoryCliente {

    private final ClienteRepositoryJpa jpa;

    public RepositoryClienteImpl(ClienteRepositoryJpa jpaRepository) {
        this.jpa = jpaRepository;
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        ClienteEntity entidad = ClienteMapper.toEntity(cliente);
        ClienteEntity guardada = jpa.save(entidad);
        return ClienteMapper.toDomain(guardada);
    }

    @Override
    public Optional<Cliente> buscarPorId(String clienteId) {
        return jpa.findById(clienteId)
                .map(ClienteMapper::toDomain);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return jpa.findAll()
                .stream()
                .map(ClienteMapper::toDomain)
                .collect(Collectors.toList());
    }
 
    @Override
    public void eliminar(String clienteId) {
        jpa.deleteById(clienteId);
    }

}
