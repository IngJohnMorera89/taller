package com.devsenior.jmorera.taller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.devsenior.jmorera.taller.model.EstadoOrden;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record OrdenServicioRequest(
        @NotBlank(message = "La descripción es obligatoria") @Size(max = 500, message = "La descripción no puede exceder 500 caracteres") String descripcion,
        @NotNull(message = "La fecha de ingreso es obligatoria") LocalDate fechaIngreso,
        LocalDate fechaEntrega,
        @NotNull(message = "El estado es obligatorio") EstadoOrden estado,
        @NotNull(message = "El costo es obligatorio") @Positive(message = "El costo debe ser mayor a cero") BigDecimal costo,
        @NotNull(message = "El vehículo es obligatorio") Long vehiculoId) {
}
