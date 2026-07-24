package com.devsenior.jmorera.taller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.devsenior.jmorera.taller.model.EstadoOrden;

public record OrdenServicioResponse(Long id, String descripcion, LocalDate fechaIngreso, LocalDate fechaEntrega,
        EstadoOrden estado, BigDecimal costo, Long vehiculoId) {
}
