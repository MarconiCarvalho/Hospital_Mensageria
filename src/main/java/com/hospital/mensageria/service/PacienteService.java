package com.hospital.mensageria.service;

import com.hospital.mensageria.models.CallHistory;
import com.hospital.mensageria.models.Paciente;
import com.hospital.mensageria.repositories.CallHistoryRepository;
import com.hospital.mensageria.repositories.PacienteRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private CallHistoryRepository callHistoryRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Paciente registerPaciente(Paciente paciente){
      paciente = pacienteRepository.save(paciente);
      rabbitTemplate.convertAndSend("fila_prioridade", paciente);
      return paciente;
    }

    public void updatePriority(Long id, int newPriority) {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        paciente.setPriority(newPriority);
        pacienteRepository.save(paciente);
        rabbitTemplate.convertAndSend("fila_prioridade", paciente);
    }

    public List<Paciente> listPacientes() {
        return pacienteRepository.findAll(Sort.by(Sort.Direction.DESC, "prioridade"));
    }

    public void removePaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        pacienteRepository.deleteById(id);


        CallHistory history = new CallHistory();
        history.setIdPaciente(paciente.getId());
        history.setDateTimeCall(LocalDateTime.now());
        history.setAction("Paciente removido");
        callHistoryRepository.save(history);

        rabbitTemplate.convertAndSend("fila_remocao", paciente);
    }
}
