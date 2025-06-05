/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cliente.adapters.in;

import com.byKrizz.cliente.adapters.in.dto.ClienteDto;
import com.byKrizz.cliente.adapters.out.repository.mapper.ClienteMapper;
import com.byKrizz.cliente.domain.model.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.byKrizz.cliente.domain.ports.in.UpdateClienteService;
import com.byKrizz.cliente.domain.ports.in.DeleteClienteService;
import com.byKrizz.cliente.domain.ports.in.FindClienteService;
import com.byKrizz.cliente.domain.ports.in.CreateClienteService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 *
 * @author chris
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final CreateClienteService createService;
    private final FindClienteService findService;
    private final UpdateClienteService updateService;
    private final DeleteClienteService deleteService;
    private final ClienteMapper mapper;

    public ClienteController(CreateClienteService createService,
            FindClienteService findService,
            UpdateClienteService updateService,
            DeleteClienteService deleteService,
            ClienteMapper mapper
    ) {
        this.createService = createService;
        this.findService = findService;
        this.updateService = updateService;
        this.deleteService = deleteService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ClienteDto> crear(@RequestBody @Valid ClienteDto clienteDto) throws Exception {
//        Cliente cliente = mapper.toDomain(clienteDto);
//        CompletableFuture<Cliente> creado = createService.crearCliente(cliente);
//        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(creado));
        Cliente cliente = mapper.toDomain(clienteDto);
        Cliente creado = createService.crearCliente(cliente).get(); // Espera a que termine
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(creado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtener(@PathVariable String id) {
        return findService.obtenerPorId(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> obtenerTodos() {
        List<Cliente> clientes = findService.obtenerTodos();
        List<ClienteDto> dtos = clientes.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> actualizar(@PathVariable String id, @RequestBody @Valid ClienteDto clienteDto) {
        Cliente cliente = mapper.toDomain(clienteDto);
        cliente.setClienteId(id); // asegurar que el ID no cambie
        Cliente actualizado = updateService.actualizarCliente(cliente);
        return ResponseEntity.ok(mapper.toDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        deleteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
