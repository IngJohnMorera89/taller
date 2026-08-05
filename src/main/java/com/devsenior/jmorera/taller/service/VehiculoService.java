package com.devsenior.jmorera.taller.service;

import java.util.List;

import com.devsenior.jmorera.taller.dto.VehiculoRequest;
import com.devsenior.jmorera.taller.dto.VehiculoResponse;

public interface VehiculoService {

    VehiculoResponse crear(VehiculoRequest vehiculoRequest);

    List<VehiculoResponse> listar();

    VehiculoResponse buscarPorId(Long id);

    List<VehiculoResponse> listarPorCliente(Long clienteId);

    void eliminar(Long id);

}
