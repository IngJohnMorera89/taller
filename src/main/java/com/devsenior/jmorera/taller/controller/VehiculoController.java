package com.devsenior.jmorera.taller.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.jmorera.taller.dto.VehiculoRequest;
import com.devsenior.jmorera.taller.dto.VehiculoResponse;
import com.devsenior.jmorera.taller.service.VehiculoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @PostMapping
    public ResponseEntity<VehiculoResponse> crear(@Valid @RequestBody VehiculoRequest vehiculoRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vehiculoService.crear(vehiculoRequest));

    }

    @GetMapping
    public ResponseEntity<List<VehiculoResponse>> listar() {
        return ResponseEntity.ok(vehiculoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponse> buscarPorId(@PathVariable Long vehiculoid) {
        return ResponseEntity.ok(vehiculoService.buscarPorId(vehiculoid));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<VehiculoResponse>> listarPorClienteId(@PathVariable Long clienteId) {
        return ResponseEntity.ok(vehiculoService.listarPorCliente(clienteId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long vehiculoid) {
        vehiculoService.eliminar(vehiculoid);
        return ResponseEntity.noContent().build();
    }

}
