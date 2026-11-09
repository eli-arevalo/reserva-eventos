package com.duoc.reservaEventos.service;

import com.duoc.reservaEventos.entity.Evento;
import java.util.Optional;
import java.util.List;

public interface EventoService  {
    Evento crearEvento(Evento evento);
    List<Evento> listarEventos();
    Optional<Evento> obtenerEventoPorId(Long id);
}
