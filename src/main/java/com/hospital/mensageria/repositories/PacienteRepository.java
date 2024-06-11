package com.hospital.mensageria.repositories;

import com.hospital.mensageria.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
