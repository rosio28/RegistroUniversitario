package com.universidad.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "docente")
public class Docente extends Persona {

    @Column(name = "nro_empleado", nullable = false, unique = true)
    private String nroEmpleado;

    @Column(name = "departamento", nullable = false)
    private String departamento;

    @OneToMany(mappedBy = "docente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvaluacionDocente> evaluaciones;

    @ManyToMany
    @JoinTable(
        name = "docente_materia",
        joinColumns = @JoinColumn(name = "docente_id"),
        inverseJoinColumns = @JoinColumn(name = "materia_id")
    )
    
    private List<Materia> materiasAsignadas;
}
