package com.devsenior.jmorera.taller.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsenior.jmorera.taller.dto.OrdenRequest;
import com.devsenior.jmorera.taller.dto.OrdenResponse;
import com.devsenior.jmorera.taller.exception.OperacionNoPermitidaException;
import com.devsenior.jmorera.taller.exception.RecursoNoEncontradoException;
import com.devsenior.jmorera.taller.model.EstadoOrden;
import com.devsenior.jmorera.taller.model.OrdenServicio;
import com.devsenior.jmorera.taller.model.Vehiculo;
import com.devsenior.jmorera.taller.repository.OrdenServicioRepository;
import com.devsenior.jmorera.taller.repository.VehiculoRepository;
import com.devsenior.jmorera.taller.service.OrdenServicioService;

@Service
@Transactional
public class OrdenServicioServiceImpl implements OrdenServicioService {

    private final OrdenServicioRepository ordenServicioRepository;

    private final VehiculoRepository vehiculoRepository;

    public OrdenServicioServiceImpl(OrdenServicioRepository ordenServicioRepository,
            VehiculoRepository vehiculoRepository) {
        this.ordenServicioRepository = ordenServicioRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    @Transactional
    public OrdenResponse crear(OrdenRequest ordenRequest) {

        Vehiculo vehiculo = vehiculoRepository.findById(ordenRequest.vehiculoId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un vehículo con el ID " + ordenRequest.vehiculoId()));

        OrdenServicio orden = new OrdenServicio();
        orden.setDescripcion(ordenRequest.descripcion());
        orden.setCosto(ordenRequest.costo());
        orden.setVehiculo(vehiculo);
        orden.setFechaIngreso(LocalDate.now());
        orden.setEstado(EstadoOrden.RECIBIDO);

        return toResponse(ordenServicioRepository.save(orden));
    }

    @Override
    public List<OrdenResponse> listar() {
        return ordenServicioRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public OrdenResponse buscarPorId(Long id) {
        return toResponse(obtenerOrden(id));
    }

    @Override
    @Transactional
    public OrdenResponse cambiarEstado(Long id, EstadoOrden nuevoestado) {

        OrdenServicio orden = obtenerOrden(id);

        if (orden.getEstado() == EstadoOrden.ENTREGADO) {
            throw new OperacionNoPermitidaException(
                    "No se puede cambiar el estado de una orden que ya ha sido entregada.");
        }

        orden.setEstado(nuevoestado);
        if (nuevoestado == EstadoOrden.ENTREGADO) {
            orden.setFechaEntrega(LocalDate.now());
        }
        return toResponse(ordenServicioRepository.save(orden));
    }

    @Override
    public List<OrdenResponse> listarPorEstado(EstadoOrden estado) {
        return ordenServicioRepository.findByEstado(estado).stream()
                .map(this::toResponse)
                .toList();
    }

    private OrdenServicio obtenerOrden(Long id) {
        return ordenServicioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe una orden con el ID " + id));
    }

    private OrdenResponse toResponse(OrdenServicio orden) {
        return new OrdenResponse(
                orden.getId(),
                orden.getDescripcion(),
                orden.getFechaIngreso(),
                orden.getFechaEntrega(),
                orden.getEstado().name(),
                orden.getCosto(),
                orden.getVehiculo().getPlaca(),
                orden.getVehiculo().getCliente().getNombre());
    }

}