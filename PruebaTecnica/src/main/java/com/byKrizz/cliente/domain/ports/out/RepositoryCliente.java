/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cliente.domain.ports.out;

import com.byKrizz.cliente.domain.model.Cliente;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author chris
 */
public interface RepositoryCliente {

    Cliente guardar(Cliente cliente);                    
    Optional<Cliente> buscarPorId(String clienteId);     
    List<Cliente> buscarTodos();                         
    void eliminar(String clienteId);                     
    
}
