package com.universidad.service.impl;

import com.universidad.model.Inscripcion;
import com.universidad.repository.InscripcionRepository;
import com.universidad.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionServiceImpl implements InscripcionService {
    @Autowired
    private InscripcionRepository repository;

    @Override
    public List<Inscripcion> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Inscripcion> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Inscripcion save(Inscripcion inscripcion) {
        return repository.save(inscripcion);
    }

    @Override
    public Inscripcion update(Long id, Inscripcion inscripcion) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Inscripción no encontrada con ID: " + id);
        }
        inscripcion.setId(id);
        return repository.save(inscripcion);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
