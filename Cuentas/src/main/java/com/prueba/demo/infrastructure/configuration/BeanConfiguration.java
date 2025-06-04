/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.demo.infrastructure.configuration;

import com.prueba.demo.application.CreateClienteServiceImpl;
import com.prueba.demo.application.DeleteClienteServiceImpl;
import com.prueba.demo.application.FindClienteServiceImpl;
import com.prueba.demo.application.UpdateClienteServiceImpl;
import com.prueba.demo.domain.ports.in.CreateClienteService;
import com.prueba.demo.domain.ports.in.DeleteClienteService;
import com.prueba.demo.domain.ports.in.FindClienteService;
import com.prueba.demo.domain.ports.in.UpdateClienteService;
import com.prueba.demo.domain.ports.out.RepositoryCliente;
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
