package com.universidad.controller;

import com.universidad.dto.InscripcionRequest;
import com.universidad.dto.InscripcionResponse;
import com.universidad.model.Estudiante;
import com.universidad.model.Inscripcion;
import com.universidad.model.Materia;
import com.universidad.repository.EstudianteRepository;
import com.universidad.repository.MateriaRepository;
import com.universidad.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService service;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @GetMapping
    public ResponseEntity<List<InscripcionResponse>> listar() {
        List<InscripcionResponse> respuesta = service.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionResponse> obtener(@PathVariable Long id) {
        return service.findById(id)
                .map(this::convertirAResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody InscripcionRequest request) {
        Estudiante estudiante = estudianteRepository.findById(request.getIdEstudiante())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Materia materia = materiaRepository.findById(request.getIdMateria())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        Inscripcion inscripcion = Inscripcion.builder()
                .estudiante(estudiante)
                .materia(materia)
                .fechaInscripcion(request.getFechaInscripcion())
                .usuarioInscripcion(request.getUsuarioInscripcion())
                .build();

        return ResponseEntity.ok(convertirAResponse(service.save(inscripcion)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody InscripcionRequest request) {
        Estudiante estudiante = estudianteRepository.findById(request.getIdEstudiante())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Materia materia = materiaRepository.findById(request.getIdMateria())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        Inscripcion inscripcion = Inscripcion.builder()
                .id(id)
                .estudiante(estudiante)
                .materia(materia)
                .fechaInscripcion(request.getFechaInscripcion())
                .usuarioInscripcion(request.getUsuarioInscripcion())
                .build();

        return ResponseEntity.ok(convertirAResponse(service.update(id, inscripcion)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private InscripcionResponse convertirAResponse(Inscripcion i) {
        InscripcionResponse dto = new InscripcionResponse();
        dto.setId(i.getId());
        dto.setNombreEstudiante(i.getEstudiante().getNombre());
        dto.setNombreMateria(i.getMateria().getNombreMateria());
        dto.setFechaInscripcion(i.getFechaInscripcion());
        dto.setUsuarioInscripcion(i.getUsuarioInscripcion());
        return dto;
    }
}
