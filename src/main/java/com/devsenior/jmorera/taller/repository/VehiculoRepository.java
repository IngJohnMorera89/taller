package com.devsenior.jmorera.taller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsenior.jmorera.taller.model.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    // Puedes agregar métodos de consulta personalizados aquí si es necesario

    boolean existsByPlaca(String placa);

    List<Vehiculo> findByClienteId(Long clienteId);

}
