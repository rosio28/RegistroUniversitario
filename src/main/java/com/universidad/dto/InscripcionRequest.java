package com.universidad.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InscripcionRequest {
    private Long idEstudiante;
    private Long idMateria;
    private LocalDate fechaInscripcion;
    private String usuarioInscripcion;
}
