package com.duoc.reservaEventos.service;

import com.duoc.reservaEventos.dto.*;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    UsuarioRespuestaDTO registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO);
    List<UsuarioRespuestaDTO> listarUsuarios();
    Optional<UsuarioRespuestaDTO> obtenerUsuarioPorId(Long id);
}
