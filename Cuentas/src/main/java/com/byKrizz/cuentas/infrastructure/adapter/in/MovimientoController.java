/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.adapter.in;

import com.byKrizz.cuentas.domain.model.Movimiento;
import com.byKrizz.cuentas.domain.ports.in.MovimientoService;
import com.byKrizz.cuentas.infrastructure.adapter.in.dto.MovimientoDto;
import com.byKrizz.cuentas.shared.exeption.FondosInsuficientesException;
import java.math.BigDecimal;
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
@RequestMapping("/movimiento")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping("/debito")
    public ResponseEntity<?> realizarDebito(@RequestBody MovimientoDto request) {
        try {
            movimientoService.debitar(
                    request.getNumeroCuenta(),
                    request.getValorMovimiento(),
                    request.getDescripcion()
            );
            return ResponseEntity.ok("Débito realizado con éxito.");
        } catch (FondosInsuficientesException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado." + e.toString());
        }
    }

    @PostMapping("/credito")
    public ResponseEntity<?> acreditar(@RequestBody MovimientoDto request) {
        try {
            if (request.getValorMovimiento() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El valor del movimiento es obligatorio.");
            }

            movimientoService.acreditar(
                    request.getNumeroCuenta(),
                    request.getValorMovimiento(),
                    request.getDescripcion()
            );
            return ResponseEntity.ok("Crédito realizado con éxito.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }

}
