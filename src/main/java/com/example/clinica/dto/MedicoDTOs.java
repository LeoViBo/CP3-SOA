package com.example.clinica.dto;

import jakarta.validation.constraints.*;

public record MedicoCreateDTO(
        @NotBlank String nome,
        @NotBlank String especialidade,
        @NotBlank String crm,
        @NotBlank @Email String email
) {}

public record MedicoResponseDTO(
        Long id,
        String nome,
        String especialidade,
        String crm,
        String email
) {}

public record MedicoUpdateDTO(
        @NotBlank String nome,
        @NotBlank String especialidade,
        @NotBlank @Email String email
) {}

