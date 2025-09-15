package com.example.clinica.repository;

import com.example.clinica.domain.model.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Page<Consulta> findByMedicoId(Long medicoId, Pageable pageable);
    boolean existsByMedicoIdAndDataHora(Long medicoId, LocalDateTime dataHora);
}
