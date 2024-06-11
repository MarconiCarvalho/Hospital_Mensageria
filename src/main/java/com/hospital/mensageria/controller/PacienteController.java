package com.hospital.mensageria.controller;

import com.hospital.mensageria.models.Paciente;
import com.hospital.mensageria.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public Paciente registerPaciente(@RequestBody Paciente paciente) {
        return pacienteService.registerPaciente(paciente);
    }

    @PutMapping("/{id}/prioridade")
    public void updatePriority(@PathVariable Long id, @RequestParam int prioridade) {
        pacienteService.updatePriority(id, prioridade);
    }

    @GetMapping
    public List<Paciente> listPacientes(){
        return pacienteService.listPacientes();
    }

    @DeleteMapping("/{id}")
    public void removePaciente(@PathVariable Long id) {
        pacienteService.removePaciente(id);
    }
}
