package com.devsenior.jmorera.taller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrdenResponse(

        Long id,
        String descripcion,
        LocalDate fechaIngreso,
        LocalDate fechaEntrega,
        String estado,
        BigDecimal costo,
        String placaVehiculo,
        String nombreCliente

) {

}
