/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cliente.domain.ports.in;

import com.byKrizz.cliente.domain.model.Cliente;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author chris
 */
public interface CreateClienteService {
//     Cliente crearCliente(Cliente cliente);
    CompletableFuture<Cliente> crearCliente(Cliente cliente);
}
