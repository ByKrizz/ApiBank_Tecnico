/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.adapter.out;

import com.byKrizz.cuentas.infrastructure.adapter.out.repository.jpa.CuentaRepositoryJpa;
import com.byKrizz.cuentas.domain.ports.out.GenerateNumeroCuentaPort;
import org.springframework.stereotype.Component;

/**
 *
 * @author chris
 */
@Component
public class GenerateNumeroCuenta  implements GenerateNumeroCuentaPort {

    private final CuentaRepositoryJpa cuentaRepository;

    public GenerateNumeroCuenta(CuentaRepositoryJpa cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public String generarNumeroCuenta() {
        long count = cuentaRepository.count(); // Suponiendo que cuentas existentes son secuenciales
        long numeroSecuencial = 100000 + count + 1;
        return String.valueOf(numeroSecuencial);
    }
}
