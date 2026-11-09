package com.duoc.reservaEventos.controller;

import com.duoc.reservaEventos.dto.*;
import com.duoc.reservaEventos.service.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController 
@RequestMapping ("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping 
    public ResponseEntity<UsuarioRespuestaDTO> registrarUsuario(@Valid @RequestBody UsuarioRegistroDTO usuarioRegistroDTO) {
        UsuarioRespuestaDTO usuarioRespuestaDTO = usuarioService.registrarUsuario(usuarioRegistroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRespuestaDTO);
    }




    @GetMapping 
    public ResponseEntity<List<UsuarioRespuestaDTO>> listarUsuarios() {
        List<UsuarioRespuestaDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<UsuarioRespuestaDTO> obtenerUsuarioPorId(@PathVariable Long id) {

        Optional<UsuarioRespuestaDTO> usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
