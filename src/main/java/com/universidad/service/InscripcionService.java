package com.universidad.service;
import com.universidad.model.Inscripcion;
import java.util.List;
import java.util.Optional;
public interface InscripcionService {
    List<Inscripcion> findAll();
    Optional<Inscripcion> findById(Long id);
    Inscripcion save(Inscripcion inscripcion);
    Inscripcion update(Long id, Inscripcion inscripcion);
    void deleteById(Long id);
}