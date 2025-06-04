/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.domain.ports.out;

import com.byKrizz.cuentas.domain.model.Cliente;
import java.util.Optional;

/**
 *
 * @author chris
 */
public interface ClienteRemoteService {

    Optional<Cliente> obtenerClientePorId(String clienteId);

}
