package com.universidad.registro.service;
import com.universidad.registro.model.Usuario;
import com.universidad.registro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UsuarioService {
     @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario guardarUsuario(Usuario usuario) throws Exception {
        // Aquí puedes agregar validaciones básicas antes de guardar
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new Exception("El nombre de usuario ya existe");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new Exception("El email ya está registrado");
        }

        // Aquí puedes agregar la lógica para cifrar la contraseña si usas Spring Security

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
