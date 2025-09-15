package com.example.clinica.controller;

import com.example.clinica.dto.ConsultaCreateDTO;
import com.example.clinica.dto.ConsultaResponseDTO;
import com.example.clinica.dto.ConsultaUpdateDTO;
import com.example.clinica.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService service;

    @Operation(summary = "Cria uma consulta")
    @ApiResponse(responseCode = "201", description = "Consulta criada")
    @PostMapping
    public ResponseEntity<Void> criar(@Valid @RequestBody ConsultaCreateDTO dto) {
        Long id = service.criar(dto);
        return ResponseEntity.created(URI.create("/consultas/" + id)).build();
    }

    @Operation(summary = "Lista consultas com paginação")
    @GetMapping
    public Page<ConsultaResponseDTO> listar(@ParameterObject Pageable pageable) {
        return service.listar(pageable);
    }

    @Operation(summary = "Busca consulta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Atualiza uma consulta")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ConsultaUpdateDTO dto) {
        service.atualizar(id, dto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remove uma consulta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
