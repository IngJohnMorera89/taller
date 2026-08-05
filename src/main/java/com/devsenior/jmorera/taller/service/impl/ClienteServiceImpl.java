package com.devsenior.jmorera.taller.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsenior.jmorera.taller.dto.ClienteRequest;
import com.devsenior.jmorera.taller.dto.ClienteResponse;
import com.devsenior.jmorera.taller.exception.RecursoDuplicadoException;
import com.devsenior.jmorera.taller.model.Cliente;
import com.devsenior.jmorera.taller.model.Vehiculo;
import com.devsenior.jmorera.taller.repository.ClienteRepository;
import com.devsenior.jmorera.taller.service.ClienteService;

@Service
@Transactional(readOnly = true)
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public ClienteResponse crear(ClienteRequest clienteRequest) {
        if (clienteRepository.existsByCedula(clienteRequest.cedula())) {
            throw new RecursoDuplicadoException("Ya existe un cliente con la cédula: " + clienteRequest.cedula());
        }

        Cliente cliente = toEntity(clienteRequest);
        return toResponse(clienteRepository.save(cliente));
    }

    private Cliente toEntity(ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNombre(request.nombre());
        cliente.setCedula(request.cedula());
        cliente.setTelefono(request.telefono());
        cliente.setEmail(request.email());
        return cliente;
    }

    private ClienteResponse toResponse(Cliente cliente) {
        List<Long> vehiculoIds = cliente.getVehiculos() == null
                ? List.of()
                : cliente.getVehiculos().stream()
                        .map(Vehiculo::getId)
                        .toList();

        return new ClienteResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getCedula(),
                cliente.getTelefono(),
                cliente.getEmail(),
                vehiculoIds);
    }

    @Override
    public List<ClienteResponse> listar() {
        return clienteRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ClienteResponse buscarPorId(Long id) {

        Cliente cliente = obtenerClientePorId(id);
        return toResponse(cliente);
    }

    @Override
    @Transactional
    public ClienteResponse actualizar(Long id, ClienteRequest clienteRequest) {
        Cliente clienteExistente = obtenerClientePorId(id);

        if (!clienteExistente.getCedula().equals(clienteRequest.cedula()) &&
                clienteRepository.existsByCedula(clienteRequest.cedula())) {
            throw new RecursoDuplicadoException("Ya existe un cliente con la cédula: " + clienteRequest.cedula());
        }

        clienteExistente.setNombre(clienteRequest.nombre());
        clienteExistente.setCedula(clienteRequest.cedula());
        clienteExistente.setTelefono(clienteRequest.telefono());
        clienteExistente.setEmail(clienteRequest.email());

        return toResponse(clienteRepository.save(clienteExistente));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Cliente cliente = obtenerClientePorId(id);
        clienteRepository.delete(cliente);
    }

    private Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id: " + id));
    }

}