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

public record PacienteUpdateDTO(
        @NotBlank String nome,
        @NotBlank @Email String email
) {}

public record ConsultaUpdateDTO(
        @NotNull @Future LocalDateTime dataHora,
        String descricao
) {}

public record ConsultaResponseDTO(
        Long id,
        Long pacienteId,
        Long medicoId,
        LocalDateTime dataHora,
        String descricao
) {}
