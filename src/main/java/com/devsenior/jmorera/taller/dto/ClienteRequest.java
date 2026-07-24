package com.devsenior.jmorera.taller.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequest(
        Long id,
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "La cédula es obligatoria") @Size(max = 20, message = "La cédula no puede exceder 20 caracteres") String cedula,
        @Size(max = 15, message = "El teléfono no puede exceder 15 caracteres") String telefono,
        @Email(message = "El email debe ser válido") String email,
        List<Long> vehiculoIds) {
}
