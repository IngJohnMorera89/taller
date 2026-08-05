package com.devsenior.jmorera.taller.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsenior.jmorera.taller.dto.VehiculoRequest;
import com.devsenior.jmorera.taller.dto.VehiculoResponse;
import com.devsenior.jmorera.taller.exception.RecursoDuplicadoException;
import com.devsenior.jmorera.taller.exception.RecursoNoEncontradoException;
import com.devsenior.jmorera.taller.model.Cliente;
import com.devsenior.jmorera.taller.model.Vehiculo;
import com.devsenior.jmorera.taller.repository.ClienteRepository;
import com.devsenior.jmorera.taller.repository.VehiculoRepository;
import com.devsenior.jmorera.taller.service.VehiculoService;

@Service
@Transactional(readOnly = true)
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final ClienteRepository clienteRepository;

    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository, ClienteRepository clienteRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public VehiculoResponse crear(VehiculoRequest vehiculoRequest) {

        if (vehiculoRepository.existsByPlaca(vehiculoRequest.placa())) {

            throw new RecursoDuplicadoException("El vehículo con placa " + vehiculoRequest.placa() + " ya existe.");
        }

        Cliente cliente = clienteRepository.findById(vehiculoRequest.clienteId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un cliente con el ID " + vehiculoRequest.clienteId()));

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca(vehiculoRequest.placa());
        vehiculo.setMarca(vehiculoRequest.marca());
        vehiculo.setModelo(vehiculoRequest.modelo());
        vehiculo.setAnio(vehiculoRequest.anio());
        vehiculo.setCliente(cliente);

        return toResponse(vehiculoRepository.save(vehiculo));
    }

    @Override
    public List<VehiculoResponse> listar() {

        return vehiculoRepository.findAll().stream()
                .map(this::toResponse)
                .toList();

    }

    @Override
    public VehiculoResponse buscarPorId(Long id) {

        return toResponse(obtenerVehiculo(id));
    }

    @Override
    public List<VehiculoResponse> listarPorCliente(Long clienteId) {

        if (!clienteRepository.existsById(clienteId)) {
            throw new RecursoNoEncontradoException(
                    "No existe un cliente con id " + clienteId);
        }
        return vehiculoRepository.findByClienteId(clienteId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public void eliminar(Long id) {

        vehiculoRepository.delete(obtenerVehiculo(id));
    }

    private Vehiculo obtenerVehiculo(Long id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un vehiculo con id " + id));
    }
    // Implementación de los métodos de la interfaz VehiculoService

    private VehiculoResponse toResponse(Vehiculo vehiculo) {

        return new VehiculoResponse(
                vehiculo.getId(),
                vehiculo.getPlaca(),
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getAnio(),
                vehiculo.getCliente().getId());
    }

}
