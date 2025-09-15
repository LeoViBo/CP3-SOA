package com.example.clinica.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record ConsultaCreateDTO(
        @NotNull Long pacienteId,
        @NotNull Long medicoId,
        @NotNull @Future LocalDateTime dataHora
) {}

public record ConsultaUpdateDTO(
        @NotNull @Future LocalDateTime dataHora,
        @NotNull String status  // String representando o StatusConsulta
) {}

public record ConsultaResponseDTO(
        Long id,
        Long pacienteId,
        Long medicoId,
        LocalDateTime dataHora,
        String status
) {}
