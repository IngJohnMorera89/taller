package com.devsenior.jmorera.taller.service;

import java.util.List;

import com.devsenior.jmorera.taller.dto.OrdenRequest;
import com.devsenior.jmorera.taller.dto.OrdenResponse;
import com.devsenior.jmorera.taller.model.EstadoOrden;

public interface OrdenServicioService {

    OrdenResponse crear(OrdenRequest ordenRequest);

    List<OrdenResponse> listar();

    OrdenResponse buscarPorId(Long id);

    OrdenResponse cambiarEstado(Long id, EstadoOrden nuevoestado);

    List<OrdenResponse> listarPorEstado(EstadoOrden estado);

}
