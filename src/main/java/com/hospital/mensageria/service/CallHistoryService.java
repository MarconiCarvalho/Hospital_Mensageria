package com.hospital.mensageria.service;

import com.hospital.mensageria.models.CallHistory;
import com.hospital.mensageria.models.Paciente;
import com.hospital.mensageria.repositories.CallHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CallHistoryService {

    @Autowired
    private CallHistoryRepository callHistoryRepository;

    public void processCalls(Paciente paciente) {
        CallHistory history = new CallHistory();
        history.setIdPaciente(paciente.getId());
        history.setDateTimeCall(LocalDateTime.now());
        history.setAction("Paciente chamado");
        callHistoryRepository.save(history);

    }

    public void processRemove(Paciente paciente) {
        System.out.println("Paciente removido: " + paciente.getId());
    }
}
