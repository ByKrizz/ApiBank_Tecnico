/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.demo.domain.ports.in;

import com.prueba.demo.domain.model.Cliente;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author chris
 */
public interface  FindClienteService {

    Optional<Cliente> obtenerPorId(String clienteId);

    List<Cliente> obtenerTodos();
}
