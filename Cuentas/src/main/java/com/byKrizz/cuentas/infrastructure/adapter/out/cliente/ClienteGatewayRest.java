/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.adapter.out.cliente;

import com.byKrizz.cuentas.domain.model.Cliente;
import com.byKrizz.cuentas.domain.ports.out.ClienteRemoteService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author chris
 */
@Component
public class ClienteGatewayRest implements ClienteRemoteService {

    private final RestTemplate restTemplate;

    @Value("${cliente.api.url}")
    private String clienteApiUrl;

    public ClienteGatewayRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Cliente> obtenerClientePorId(String clienteId) {
//        try {
//            ResponseEntity<Cliente> response = restTemplate.getForEntity(
//                    clienteApiUrl+ "/clientes/"  + clienteId,
//                    Cliente.class
//            );
//            return Optional.ofNullable(response.getBody());
//        } catch (HttpClientErrorException.NotFound e) {
//            return Optional.empty();
//        }
        try {
            ResponseEntity<Cliente> response = restTemplate.getForEntity(
                    clienteApiUrl + "/clientes/" + clienteId,
                    Cliente.class
            );

            Cliente cliente = response.getBody();
            // Validar que no sea un "cliente vacío"
            if (cliente == null || cliente.getNombres() == null) {
                return Optional.empty();
            }

            return Optional.of(cliente);

        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }
}
