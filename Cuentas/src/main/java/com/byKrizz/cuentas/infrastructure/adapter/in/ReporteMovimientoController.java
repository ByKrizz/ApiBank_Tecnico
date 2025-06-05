/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.adapter.in;

import com.byKrizz.cuentas.domain.model.Movimiento;
import com.byKrizz.cuentas.domain.ports.in.MovimientoService;
import com.byKrizz.cuentas.infrastructure.adapter.in.dto.MovimientoDto;
import com.byKrizz.cuentas.infrastructure.adapter.in.dto.ReporteDto;
import com.byKrizz.cuentas.infrastructure.adapter.out.repository.entidad.MovimientoEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chris
 */
@RestController
@RequestMapping("/reportes")
public class ReporteMovimientoController {
        private final MovimientoService movimientoService;

    public ReporteMovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerReportePorRango(
            @RequestParam String numeroCuenta,
            @RequestParam String fecha // formato esperado: "2025-06-01T00:00:00_2025-06-05T23:59:59"
    ) {
        try {
            String[] fechas = fecha.split("_");
            if (fechas.length != 2) {
                return ResponseEntity.badRequest().body("Formato de fecha inválido. Use: yyyy-MM-ddTHH:mm:ss_yyyy-MM-ddTHH:mm:ss");
            }

            LocalDateTime desde = LocalDateTime.parse(fechas[0]);
            LocalDateTime hasta = LocalDateTime.parse(fechas[1]);

            List<ReporteDto> movimientos = movimientoService.findByCuentaClienteIdAndFechaMovimientoBetween(numeroCuenta, desde, hasta);
            return ResponseEntity.ok(movimientos);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error procesando el reporte: " + e.getMessage());
        }
    }
}
