package com.example.clinica.service;

import com.example.clinica.domain.model.Paciente;
import com.example.clinica.domain.model.vo.Cpf;
import com.example.clinica.domain.model.vo.Email;
import com.example.clinica.dto.PacienteCreateDTO;
import com.example.clinica.dto.PacienteResponseDTO;
import com.example.clinica.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repo;

    @Transactional
    public Long criar(PacienteCreateDTO dto) {
        repo.findByCpfValue(dto.cpf()).ifPresent(p -> {
            throw new IllegalArgumentException("CPF já cadastrado");
        });
        Paciente paciente = Paciente.builder()
                .nome(dto.nome())
                .cpf(new Cpf(dto.cpf()))
                .email(new Email(dto.email()))
                .build();
        return repo.save(paciente).getId();
    }

    @Transactional(readOnly = true)
    public Page<PacienteResponseDTO> listar(Pageable pageable) {
        return repo.findAll(pageable)
                .map(p -> new PacienteResponseDTO(
                        p.getId(), p.getNome(),
                        p.getCpf().getValue(), p.getEmail().getValue()));
    }

    @Transactional(readOnly = true)
    public PacienteResponseDTO getById(Long id) {
    Paciente p = repo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
    return new PacienteResponseDTO(p.getId(), p.getNome(), p.getCpf().getValue(), p.getEmail().getValue());
    }
    
    @Transactional
    public void atualizar(Long id, PacienteCreateDTO dto) {
        Paciente p = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
        if (!p.getCpf().getValue().equals(dto.cpf())) {
            repo.findByCpfValue(dto.cpf())
                .ifPresent(existing -> { throw new IllegalArgumentException("CPF já cadastrado"); });
            p.setCpf(new Cpf(dto.cpf()));
            }
        p.setNome(dto.nome());
        p.setEmail(new Email(dto.email()));
        repo.save(p);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Paciente não encontrado");
            }
        repo.deleteById(id);
    }
}
