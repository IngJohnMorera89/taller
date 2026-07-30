package com.devsenior.jmorera.taller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsenior.jmorera.taller.model.EstadoOrden;
import com.devsenior.jmorera.taller.model.OrdenServicio;

public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, Long> {
    // Puedes agregar métodos de consulta personalizados aquí si es necesario

    List<OrdenServicio> findByVehiculoId(Long vehiculoId);

    List<OrdenServicio> findByEstado(EstadoOrden estado);

}
