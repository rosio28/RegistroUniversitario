package com.universidad.service;

import com.universidad.model.Docente;

import java.util.List;

public interface IDocenteService {
    List<Docente> listarTodos();
    Docente guardar(Docente docente);
    void eliminar(Long id);
    Docente obtenerPorId(Long id);
    void asignarMateria(Long docenteId, Long materiaId);
    Docente asignarMaterias(Long docenteId, List<Long> idsMaterias);
}
