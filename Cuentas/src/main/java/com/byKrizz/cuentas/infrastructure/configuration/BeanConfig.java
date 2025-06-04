/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.configuration;

import com.byKrizz.cuentas.domain.ports.out.ClienteRemoteService;
import com.byKrizz.cuentas.domain.ports.out.CuentaRepository;
import com.byKrizz.cuentas.infrastructure.adapter.CuentaRepositoryAdapter;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.jpa.CuentaRepositoryJpa;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.mapper.CuentaMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author chris
 */
@Configuration
public class BeanConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CuentaMapper cuentaMapper() {
        return new CuentaMapper();
    }

    @Bean
    public CuentaRepository cuentaRepository(CuentaRepositoryJpa jpa, CuentaMapper mapper) {
        return new CuentaRepositoryAdapter(jpa, mapper);
    }
//    @Bean
//    public CreateCuenta crearCuentaCasoUso(CuentaRepository repo, ClienteRemoteService clienteService) {
//        return new CuentaService(repo, clienteService);
//    }
}
