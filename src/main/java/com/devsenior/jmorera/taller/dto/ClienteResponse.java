package com.devsenior.jmorera.taller.dto;

import java.util.List;

public record ClienteResponse(Long id, String nombre, String cedula, String telefono, String email, List<Long> vehiculoIds) {
}
