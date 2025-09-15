package com.example.clinica.service;

import com.example.clinica.domain.model.Consulta;
import com.example.clinica.domain.model.Medico;
import com.example.clinica.domain.model.Paciente;
import com.example.clinica.domain.model.StatusConsulta;
import com.example.clinica.dto.ConsultaCreateDTO;
import com.example.clinica.dto.ConsultaResponseDTO;
import com.example.clinica.dto.ConsultaUpdateDTO;
import com.example.clinica.repository.ConsultaRepository;
import com.example.clinica.repository.MedicoRepository;
import com.example.clinica.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepo;
    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;

    @Transactional
    public Long criar(ConsultaCreateDTO dto) {
        Paciente paciente = pacienteRepo.findById(dto.pacienteId())
                .orElseThrow(() -> new NoSuchElementException("Paciente não encontrado"));
        Medico medico = medicoRepo.findById(dto.medicoId())
                .orElseThrow(() -> new NoSuchElementException("Médico não encontrado"));

        Consulta consulta = Consulta.builder()
                .paciente(paciente)
                .medico(medico)
                .dataHora(dto.dataHora())
                .status(StatusConsulta.AGENDADA) // status inicial
                .build();

        return consultaRepo.save(consulta).getId();
    }

    @Transactional(readOnly = true)
    public Page<ConsultaResponseDTO> listar(Pageable pageable) {
        return consultaRepo.findAll(pageable)
                .map(c -> new ConsultaResponseDTO(
                        c.getId(),
                        c.getPaciente().getId(),
                        c.getMedico().getId(),
                        c.getDataHora(),
                        c.getStatus().name()
                ));
    }

    @Transactional(readOnly = true)
    public ConsultaResponseDTO getById(Long id) {
        Consulta c = consultaRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Consulta não encontrada"));
        return new ConsultaResponseDTO(
                c.getId(),
                c.getPaciente().getId(),
                c.getMedico().getId(),
                c.getDataHora(),
                c.getStatus().name()
        );
    }

    @Transactional
    public void atualizar(Long id, ConsultaUpdateDTO dto) {
        Consulta c = consultaRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Consulta não encontrada"));

        c.setDataHora(dto.dataHora());
        c.setStatus(StatusConsulta.valueOf(dto.status().toUpperCase())); // converte string para enum

        consultaRepo.save(c);
    }

    @Transactional
    public void deletar(Long id) {
        if (!consultaRepo.existsById(id)) {
            throw new NoSuchElementException("Consulta não encontrada");
        }
        consultaRepo.deleteById(id);
    }
}
