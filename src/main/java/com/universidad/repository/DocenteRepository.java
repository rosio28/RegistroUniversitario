package com.universidad.repository;

import com.universidad.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
    // Puedes agregar métodos personalizados si lo necesitas
List<Docente> findByDepartamento(String departamento);
boolean existsByNroEmpleado(String nroEmpleado);
}
