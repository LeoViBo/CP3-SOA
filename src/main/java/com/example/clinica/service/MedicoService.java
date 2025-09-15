package com.example.clinica.service;

import com.example.clinica.domain.model.Medico;
import com.example.clinica.dto.MedicoDTOs;
import com.example.clinica.dto.MedicoDTOs.MedicoCreateDTO;
import com.example.clinica.dto.MedicoDTOs.MedicoResponseDTO;
import com.example.clinica.dto.MedicoDTOs.MedicoUpdateDTO;
import com.example.clinica.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository repo;

    @Transactional
    public Long criar(MedicoCreateDTO dto) {
        repo.findByCrm(dto.getCrm()).ifPresent(m -> {
            throw new IllegalArgumentException("CRM já cadastrado");
        });

        Medico medico = Medico.builder()
                .nome(dto.getNome())
                .especialidade(dto.getEspecialidade())
                .crm(dto.getCrm())
                .build();

        return repo.save(medico).getId();
    }

    @Transactional(readOnly = true)
    public Page<MedicoResponseDTO> listar(Pageable pageable) {
        return repo.findAll(pageable)
                .map(m -> new MedicoResponseDTO(
                        m.getId(), m.getNome(), m.getEspecialidade(), m.getCrm()));
    }

    @Transactional(readOnly = true)
    public MedicoResponseDTO getById(Long id) {
        Medico m = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Médico não encontrado"));
        return new MedicoResponseDTO(m.getId(), m.getNome(), m.getEspecialidade(), m.getCrm());
    }

    @Transactional
    public void atualizar(Long id, MedicoUpdateDTO dto) {
        Medico m = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Médico não encontrado"));

        m.setNome(dto.getNome());
        m.setEspecialidade(dto.getEspecialidade());
        // CRM não é alterável aqui; se quiser permitir, adiciona validação como no create

        repo.save(m);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Médico não encontrado");
        }
        repo.deleteById(id);
    }
}
