/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.adapter.in;

import com.byKrizz.cuentas.domain.model.Cliente;
import com.byKrizz.cuentas.domain.model.Cuenta;
import com.byKrizz.cuentas.domain.ports.in.CreateCuentaService;
import com.byKrizz.cuentas.infrastructure.adapter.in.dto.CuentaDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chris
 */
@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private final CreateCuentaService createCuentaService;

    public CuentaController(CreateCuentaService createCuentaService) {
        this.createCuentaService = createCuentaService;
    }

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@Valid @RequestBody CuentaDto cuentaDto) {
        Cliente cliente = new Cliente();
        cliente.setClienteId(cuentaDto.getClienteId());

        Cuenta cuenta = new Cuenta();
        cuenta.setTipo(cuentaDto.getTipo());
        cuenta.setSaldoInicial(cuentaDto.getSaldoInicial());
        cuenta.setCliente(cliente);

        Cuenta cuentaCreada = createCuentaService.crearCuenta(cuenta);
        return new ResponseEntity<>(cuentaCreada, HttpStatus.CREATED);
    }
}
