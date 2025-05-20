package com.universidad.service.impl;

import com.universidad.model.Docente;
import com.universidad.model.Materia;
import com.universidad.repository.DocenteRepository;
import com.universidad.repository.MateriaRepository;
import com.universidad.service.IDocenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocenteServiceImpl implements IDocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public List<Docente> listarTodos() {
        return docenteRepository.findAll();
    }

    @Override
    public Docente guardar(Docente docente) {
        return docenteRepository.save(docente);
    }

    @Override
    public void eliminar(Long id) {
        docenteRepository.deleteById(id);
    }

    @Override
    public Docente obtenerPorId(Long id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Docente no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public void asignarMateria(Long docenteId, Long materiaId) {
        Docente docente = docenteRepository.findById(docenteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Docente no encontrado"));

        Materia materia = materiaRepository.findById(materiaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Materia no encontrada"));

        if (docente.getMateriasAsignadas() == null) {
            docente.setMateriasAsignadas(new ArrayList<>());
        }

        if (!docente.getMateriasAsignadas().contains(materia)) {
            docente.getMateriasAsignadas().add(materia);
        }

        docenteRepository.save(docente);
    }

    @Override
    @Transactional
    public Docente asignarMaterias(Long docenteId, List<Long> idsMaterias) {
        Docente docente = docenteRepository.findById(docenteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Docente no encontrado"));

        List<Materia> materias = materiaRepository.findAllById(idsMaterias);

        if (materias.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ninguna materia encontrada con los IDs proporcionados");
        }

        if (docente.getMateriasAsignadas() == null) {
            docente.setMateriasAsignadas(new ArrayList<>());
        }

        for (Materia materia : materias) {
            if (!docente.getMateriasAsignadas().contains(materia)) {
                docente.getMateriasAsignadas().add(materia);
            }
        }

        return docenteRepository.save(docente);
    }
}
