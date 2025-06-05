/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cliente.infrastructure.configuration;

import com.byKrizz.cliente.application.CreateClienteServiceImpl;
import com.byKrizz.cliente.application.DeleteClienteServiceImpl;
import com.byKrizz.cliente.application.FindClienteServiceImpl;
import com.byKrizz.cliente.application.UpdateClienteServiceImpl;
import com.byKrizz.cliente.domain.ports.in.CreateClienteService;
import com.byKrizz.cliente.domain.ports.in.DeleteClienteService;
import com.byKrizz.cliente.domain.ports.in.FindClienteService;
import com.byKrizz.cliente.domain.ports.in.UpdateClienteService;
import com.byKrizz.cliente.domain.ports.out.RepositoryCliente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author chris
 */
@Configuration
public class BeanConfiguration {
     @Bean
    public CreateClienteService crearClienteServicio(RepositoryCliente repositorio) {
        return new CreateClienteServiceImpl(repositorio);
    }

    @Bean
    public FindClienteService buscarClienteServicio(RepositoryCliente repositorio) {
        return new FindClienteServiceImpl(repositorio);
    }

    @Bean
    public UpdateClienteService actualizarClienteServicio(RepositoryCliente repositorio) {
        return new UpdateClienteServiceImpl(repositorio);
    }

    @Bean
    public DeleteClienteService eliminarClienteServicio(RepositoryCliente repositorio) {
        return new DeleteClienteServiceImpl(repositorio);
    }
}
