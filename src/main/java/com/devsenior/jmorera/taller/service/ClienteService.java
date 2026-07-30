package com.devsenior.jmorera.taller.service;

import java.util.List;

import com.devsenior.jmorera.taller.dto.ClienteRequest;
import com.devsenior.jmorera.taller.dto.ClienteResponse;

public interface ClienteService {

    ClienteResponse crear(ClienteRequest clienteRequest);

    List<ClienteResponse> listar();

    ClienteResponse buscarPorId(Long id);

    ClienteResponse actualizar(Long id, ClienteRequest clienteRequest);

    void eliminar(Long id);
}
