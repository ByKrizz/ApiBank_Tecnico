/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.demo.infrastructure.adapters.out.repository.mapper;

import com.byKrizz.demo.infrastructure.adapters.in.dto.ClienteDto;
import com.byKrizz.demo.infrastructure.adapters.out.repository.entidad.ClienteEntity;
import com.byKrizz.cliente.domain.model.Cliente;
import org.springframework.stereotype.Component;

/**
 *
 * @author chris
 */
@Component
public class ClienteMapper {
    public static ClienteEntity toEntity(Cliente cliente) {
        ClienteEntity entidad = new ClienteEntity();
        entidad.setClienteId(cliente.getClienteId());
        entidad.setNombres(cliente.getNombres());
        entidad.setDireccion(cliente.getDireccion());
        entidad.setTelefono(cliente.getTelefono());
        entidad.setContrasena(cliente.getContrasena());
        entidad.setEstado(cliente.isEstado());
                entidad.setIdentificacion(cliente.getIdentificacion());

        return entidad;
    }

    public static Cliente toDomain(ClienteEntity entidad) {
        Cliente cliente = new Cliente();
        cliente.setClienteId(entidad.getClienteId());
        cliente.setNombres(entidad.getNombres());
        cliente.setDireccion(entidad.getDireccion());
        cliente.setTelefono(entidad.getTelefono());
        cliente.setContrasena(entidad.getContrasena());
        cliente.setEstado(entidad.isEstado());
        cliente.setIdentificacion(entidad.getIdentificacion());
        return cliente;
    } 
    public ClienteDto toDto(Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        dto.setClienteId(cliente.getClienteId());
         dto.setContrasena(cliente.getContrasena()); // ⚠️ omitir en respuesta si es sensible
        dto.setEstado(cliente.isEstado());
        dto.setIdentificacion(cliente.getIdentificacion());
        dto.setNombres(cliente.getNombres());
        dto.setDireccion(cliente.getDireccion());
        dto.setTelefono(cliente.getTelefono());
        return dto;
    }
    public Cliente toDomain(ClienteDto dto) {
        Cliente cliente = new Cliente();
        cliente.setClienteId(dto.getClienteId());
        cliente.setContrasena(dto.getContrasena());
        cliente.setEstado(dto.getEstado());
        cliente.setIdentificacion(dto.getIdentificacion());
        cliente.setNombres(dto.getNombres());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        return cliente;
    }
}
