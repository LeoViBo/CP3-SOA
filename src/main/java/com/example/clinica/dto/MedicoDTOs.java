package com.cp3.soa.dto;

import jakarta.validation.constraints.NotBlank;

public class MedicoDTOs {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Especialidade é obrigatória")
    private String especialidade;

    @NotBlank(message = "CRM é obrigatório")
    private String crm;
}
