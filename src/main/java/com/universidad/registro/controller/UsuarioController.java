package com.universidad.registro.controller;


import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universidad.registro.model.Usuario;
import com.universidad.registro.service.UsuarioService;



@CrossOrigin(origins = "*", maxAge = 3600) // Permite el acceso desde cualquier origen (CORS)
// maxAge = 3600 significa que la configuración de CORS se mantendrá durante 1 hora
@RestController
@RequestMapping("/api")
public class UsuarioController { // Controlador para manejar las peticiones relacionadas con los usuarios
    // Aquí puedes inyectar los servicios necesarios para manejar la lógica de negocio relacionada con los usuarios
    

    @GetMapping("/public/test") // Endpoint público accesible sin autenticación
    @PreAuthorize("permitAll()") // Permite el acceso a todos los usuarios, sin importar su rol
    public String allAccess() {
        return "Contenido público";
    }

    // Endpoint protegido que requiere autenticación y autorización
    // Permite el acceso a usuarios con los roles 'ESTUDIANTE', 'DOCENTE' o 'ADMIN'
    @GetMapping("/estudiantes/test")
    @PreAuthorize("hasRole('ESTUDIANTE') or hasRole('DOCENTE') or hasRole('ADMIN')") 
    public String estudianteAccess() {
        return "Contenido para estudiantes";
    }

    // Endpoint protegido que requiere autenticación y autorización
    // Permite el acceso a usuarios con los roles 'DOCENTE' o 'ADMIN'
    @GetMapping("/docentes/test")
    @PreAuthorize("hasRole('DOCENTE') or hasRole('ADMIN')")
    public String docenteAccess() {
        return "Contenido para docentes";
    }

    // Endpoint protegido que requiere autenticación y autorización
    // Permite el acceso solo a usuarios con el rol 'ADMIN'
    @GetMapping("/admin/test")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Contenido para administradores";
    }

   @Autowired
private UsuarioService usuarioService;


    // Otros endpoints GET que ya tienes...

    @PostMapping("/usuarios")
public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
    try {
        Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    } catch (Exception e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 500);
        response.put("mensaje", "Error interno del servidor");
        response.put("detalles", e.getMessage());
        return ResponseEntity.status(500).body(response);
    }
}

}

