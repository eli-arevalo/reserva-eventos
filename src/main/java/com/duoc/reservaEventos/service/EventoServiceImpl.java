package com.duoc.reservaEventos.service;

import org.springframework.stereotype.Service;
import com.duoc.reservaEventos.entity.Evento;
import com.duoc.reservaEventos.repository.EventoRepository;

import java.util.Optional;
import java.util.List;

@Service 
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;

    public EventoServiceImpl(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Override
    public Evento crearEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override 
    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public Optional<Evento> obtenerEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

}
