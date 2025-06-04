/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byKrizz.cuentas.infrastructure.adapter.in.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author chris
 */
@Getter
@Setter
public class CuentaDto {
    @NotBlank
    private String tipo;

    @NotNull(message = "El saldo inicial no puede ser nulo")
    @Positive(message = "El saldo inicial debe ser positivo")
    private BigDecimal saldoInicial;

     @NotBlank(message = "El ID del cliente es obligatorio")
    private String clienteId;
}
