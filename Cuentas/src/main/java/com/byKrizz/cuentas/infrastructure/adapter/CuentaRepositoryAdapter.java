/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.adapter;

import com.byKrizz.cuentas.domain.model.Cuenta;
import com.byKrizz.cuentas.domain.ports.out.CuentaRepository;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.jpa.CuentaRepositoryJpa;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.mapper.CuentaMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author chris
 */
public class CuentaRepositoryAdapter implements CuentaRepository {

    private final CuentaRepositoryJpa cuentaRepositoryJpa;
    private final CuentaMapper cuentaMapper;

    public CuentaRepositoryAdapter(CuentaRepositoryJpa cuentaRepositoryJpa, CuentaMapper cuentaMapper) {
        this.cuentaRepositoryJpa = cuentaRepositoryJpa;
        this.cuentaMapper = cuentaMapper;
    }

    @Override
    public Cuenta guardar(Cuenta cuenta) {
        return cuentaMapper.toDomain(
                cuentaRepositoryJpa.save(cuentaMapper.toEntity(cuenta))
        );
    }

    @Override
    public List<Cuenta> obtenerTodas() {
        return cuentaRepositoryJpa.findAll()
                .stream()
                .map(CuentaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(String numeroCuenta) {
        cuentaRepositoryJpa.deleteById(numeroCuenta);
    }

    @Override
    public long count() {
        return cuentaRepositoryJpa.count();
    }

    @Override
    public List<Cuenta> obtenerPorClienteId(String clienteId) {
        return cuentaRepositoryJpa.findAll()
                .stream()
                .filter(c -> c.getClienteId().equals(clienteId))
                .map(CuentaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cuenta> obtenerPorNumero(String numeroCuenta) {
        return cuentaRepositoryJpa.findById(numeroCuenta)
                .map(CuentaMapper::toDomain);
    }
}
