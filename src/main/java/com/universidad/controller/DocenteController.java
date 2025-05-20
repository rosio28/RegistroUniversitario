package com.universidad.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import com.universidad.model.Docente;
import com.universidad.service.IDocenteService; // Add this import or adjust the package as needed

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/docentes")
public class DocenteController {

    @Autowired
    private IDocenteService docenteService;

    @GetMapping
    public List<Docente> listarTodos() {
        return docenteService.listarTodos();
    }

    @PostMapping
    public Docente crearDocente(@RequestBody Docente docente) {
        return docenteService.guardar(docente);
    }

    @DeleteMapping("/{id}")
    public void eliminarDocente(@PathVariable Long id) {
        docenteService.eliminar(id);
    }

    @GetMapping("/{id}")
    public Docente obtenerDocentePorId(@PathVariable Long id) {
        return docenteService.obtenerPorId(id);
    }

    @PostMapping("/{docenteId}/materias/{materiaId}")
    public ResponseEntity<?> asignarMateria(@PathVariable Long docenteId, @PathVariable Long materiaId) {
        try {
            docenteService.asignarMateria(docenteId, materiaId);
            return ResponseEntity.ok("Materia asignada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
