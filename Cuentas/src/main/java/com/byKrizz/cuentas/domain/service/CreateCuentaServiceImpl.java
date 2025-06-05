/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.domain.service;

import com.byKrizz.cuentas.domain.model.Cliente;
import com.byKrizz.cuentas.domain.model.Cuenta;
import com.byKrizz.cuentas.domain.ports.in.CreateCuentaService;
import com.byKrizz.cuentas.domain.ports.out.ClienteRemoteService;
import com.byKrizz.cuentas.domain.ports.out.CuentaRepository;
import com.byKrizz.cuentas.infrastructure.adapter.out.GenerateNumeroCuenta;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
public class CreateCuentaServiceImpl implements CreateCuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRemoteService clienteRemoteService;
    private final GenerateNumeroCuenta generadorNumeroCuentaPort;

    public CreateCuentaServiceImpl(CuentaRepository cuentaRepository,
            ClienteRemoteService clienteRemoteService,
            GenerateNumeroCuenta generadorNumeroCuentaPort) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRemoteService = clienteRemoteService;
        this.generadorNumeroCuentaPort = generadorNumeroCuentaPort;
    }

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        Optional<Cliente> cliente = clienteRemoteService.obtenerClientePorId(cuenta.getCliente().getClienteId());

        if (cliente.isEmpty() || !cliente.get().isEstado()) {
            throw new IllegalArgumentException("Cliente no válido o inactivo");
        }
        cuenta.setNumeroCuenta(generadorNumeroCuentaPort.generarNumeroCuenta());
        cuenta.setCliente(cliente.get());
        cuenta.setEstado(true);
        if (cuenta.getSaldoInicial() == null) {
            cuenta.setSaldoInicial(java.math.BigDecimal.ZERO);
        }

        return cuentaRepository.guardar(cuenta);
    }

    @Override
    public Optional<Cuenta> obtenerPorNumero(String numeroCuenta) {
        return cuentaRepository.obtenerPorNumero(numeroCuenta);
    }

    @Override
    public List<Cuenta> obtenerTodas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(String numeroCuenta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cuenta> obtenerPorClienteId(String clienteId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
