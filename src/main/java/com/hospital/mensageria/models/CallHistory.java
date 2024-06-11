package com.hospital.mensageria.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "TB_CALLHISTORY")
public class CallHistory implements Serializable {

    private static long serializable = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idPaciente;
    private LocalDateTime dateTimeCall;
    private String action;

}
