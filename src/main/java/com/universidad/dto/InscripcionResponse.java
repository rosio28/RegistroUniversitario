package com.universidad.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InscripcionResponse {
    private Long id;
    private String nombreEstudiante;
    private String nombreMateria;
    private LocalDate fechaInscripcion;
    private String usuarioInscripcion;
}
