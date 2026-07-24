package com.devsenior.jmorera.taller.dto;

public record VehiculoResponse(Long id, String placa, String marca, String modelo, Integer anio, Long clienteId) {
}
