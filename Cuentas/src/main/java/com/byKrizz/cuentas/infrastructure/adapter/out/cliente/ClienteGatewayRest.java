/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.adapter.out.cliente;

import com.byKrizz.cuentas.domain.model.Cliente;
import com.byKrizz.cuentas.domain.ports.out.ClienteRemoteService;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author chris
 */
public class ClienteGatewayRest implements ClienteRemoteService  {
    private final RestTemplate restTemplate;

    public ClienteGatewayRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Cliente> obtenerClientePorId(String clienteId) {
        try {
            ResponseEntity<Cliente> response = restTemplate.getForEntity(
                "http://localhost:8080/clientes/" + clienteId,
                Cliente.class
            );
            return Optional.ofNullable(response.getBody());
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }
}
