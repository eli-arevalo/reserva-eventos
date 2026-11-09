package com.duoc.reservaEventos.service;

import org.springframework.stereotype.Service;
import com.duoc.reservaEventos.repository.UsuarioRepository;
import com.duoc.reservaEventos.entity.Usuario;
import com.duoc.reservaEventos.dto.*;
import java.util.List;
import java.util.Optional;

@Service 
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioRespuestaDTO registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) {

        Usuario usuario = new Usuario();

        usuario.setNombre(usuarioRegistroDTO.getNombre());

        if (usuarioRepository.existsByEmail(usuarioRegistroDTO.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado");
        }
        usuario.setEmail(usuarioRegistroDTO.getEmail());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return new UsuarioRespuestaDTO(usuarioGuardado.getIdUsuario(), usuarioGuardado.getNombre(), usuarioGuardado.getEmail());

    }

    @Override 
    public List<UsuarioRespuestaDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioRespuestaDTO(usuario.getIdUsuario(), usuario.getNombre(), usuario.getEmail()))
                .toList();
    }

    @Override
    public Optional<UsuarioRespuestaDTO> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> new UsuarioRespuestaDTO(usuario.getIdUsuario(), usuario.getNombre(), usuario.getEmail()));
    }

   
 }



