package com.devsenior.jmorera.taller.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OrdenRequest(

        @NotBlank(message = "La descripcion es obligatoria") @Size(min = 10, max = 500, message = "La descripcion debe tener entre 10 y 500 caracteres") String descripcion,

        @NotNull(message = "El costo es obligatorio") @DecimalMin(value = "0.0", inclusive = false, message = "El costo debe ser mayor a cero") BigDecimal costo,

        @NotNull(message = "El vehiculo es obligatorio") Long vehiculoId) {

}
