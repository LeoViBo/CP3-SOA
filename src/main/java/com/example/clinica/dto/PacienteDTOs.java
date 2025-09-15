package com.example.clinica.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record PacienteCreateDTO(
        @NotBlank String nome,
        @NotBlank @Pattern(regexp="\\d{11}") String cpf,
        @NotBlank @Email String email
) {}

public record PacienteResponseDTO(Long id, String nome, String cpf, String email) {}

public record ConsultaCreateDTO(
        @NotNull Long pacienteId,
        @NotNull Long medicoId,
        @NotNull @Future LocalDateTime dataHora
) {}
