package com.devsenior.jmorera.taller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VehiculoRequest(
        Long id,
        @NotBlank(message = "La placa es obligatoria") @Size(max = 10, message = "La placa no puede exceder 10 caracteres") String placa,
        @NotBlank(message = "La marca es obligatoria") @Size(max = 50, message = "La marca no puede exceder 50 caracteres") String marca,
        @NotBlank(message = "El modelo es obligatorio") @Size(max = 50, message = "El modelo no puede exceder 50 caracteres") String modelo,
        @NotNull(message = "El año es obligatorio") @Min(value = 1900, message = "El año debe ser mayor o igual a 1900") Integer anio,
        @NotNull(message = "El cliente es obligatorio") Long clienteId) {
}
