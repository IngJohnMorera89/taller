package com.devsenior.jmorera.taller.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.jmorera.taller.dto.OrdenRequest;
import com.devsenior.jmorera.taller.dto.OrdenResponse;
import com.devsenior.jmorera.taller.model.EstadoOrden;
import com.devsenior.jmorera.taller.service.OrdenServicioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenServicioController {

    private final OrdenServicioService ordenService;

    public OrdenServicioController(OrdenServicioService ordenServicioService) {
        this.ordenService = ordenServicioService;
    }

    @PostMapping
    public ResponseEntity<OrdenResponse> crear(@Valid @RequestBody OrdenRequest ordenRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ordenService.crear(ordenRequest));
    }

    @GetMapping
    public ResponseEntity<List<OrdenResponse>> listar() {
        return ResponseEntity.ok(ordenService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.buscarPorId(id));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<OrdenResponse> actualizarEstado(@PathVariable Long id, @RequestParam EstadoOrden estado) {
        return ResponseEntity.ok(ordenService.cambiarEstado(id, estado));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OrdenResponse>> listarPorEstado(@PathVariable EstadoOrden estado) {
        return ResponseEntity.ok(ordenService.listarPorEstado(estado));
    }

}
