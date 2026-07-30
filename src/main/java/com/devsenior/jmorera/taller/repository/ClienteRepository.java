package com.devsenior.jmorera.taller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsenior.jmorera.taller.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Puedes agregar métodos de consulta personalizados aquí si es necesario

    boolean existsByCedula(String cedula);

}
