package com.example.clinica.controller;

import com.example.clinica.dto.PacienteDTOs;
import com.example.clinica.dto.PacienteDTOs.PacienteCreateDTO;
import com.example.clinica.dto.PacienteDTOs.PacienteResponseDTO;
import com.example.clinica.dto.PacienteDTOs.PacienteUpdateDTO;
import com.example.clinica.service.PacienteService;
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
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    @Operation(summary = "Cria um paciente")
    @ApiResponse(responseCode = "201", description = "Paciente criado")
    @PostMapping
    public ResponseEntity<Void> criar(@Valid @RequestBody PacienteCreateDTO dto) {
        Long id = service.criar(dto);
        return ResponseEntity.created(URI.create("/pacientes/" + id)).build();
    }

    @Operation(summary = "Lista pacientes com paginação")
    @GetMapping
    public Page<PacienteResponseDTO> listar(@ParameterObject Pageable pageable) {
        return service.listar(pageable);
    }

    @Operation(summary = "Busca paciente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Atualiza um paciente")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PacienteUpdateDTO dto) {
        service.atualizar(id, dto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remove um paciente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
