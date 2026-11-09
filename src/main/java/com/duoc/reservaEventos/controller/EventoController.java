package com.duoc.reservaEventos.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.duoc.reservaEventos.service.EventoService;
import com.duoc.reservaEventos.entity.Evento;

@RestController 
@RequestMapping ("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }


    @PostMapping
    public ResponseEntity<Evento> crearEvento(@Valid @RequestBody Evento evento) {
        Evento nuevoEvento = eventoService.crearEvento(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEvento);
    }

    @GetMapping 
    public ResponseEntity<List<Evento>> listarEventos(){
        return ResponseEntity.ok(eventoService.listarEventos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerEventoPorId(@PathVariable Long id) {
        
        Optional<Evento> evento = eventoService.obtenerEventoPorId(id);
        if (evento.isPresent()) {
            return ResponseEntity.ok(evento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
