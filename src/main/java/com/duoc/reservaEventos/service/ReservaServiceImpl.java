package com.duoc.reservaEventos.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.duoc.reservaEventos.dto.ReservaRespuestaDTO;
import com.duoc.reservaEventos.dto.ReservaRegistroDTO;
import com.duoc.reservaEventos.repository.ReservaRepository;
import com.duoc.reservaEventos.repository.EventoRepository;
import com.duoc.reservaEventos.repository.UsuarioRepository;
import com.duoc.reservaEventos.entity.Evento;
import com.duoc.reservaEventos.entity.Usuario;
import com.duoc.reservaEventos.entity.Reserva;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service 
public class ReservaServiceImpl implements ReservaService {
    private final ReservaRepository reservaRepository;
    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;


    public ReservaServiceImpl(ReservaRepository reservaRepository, EventoRepository eventoRepository, UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)//esto permite que la consulta con bloqueo, la comprobacion de disp y el guardado de la reserva ocurran dentro de la misma transaccion ;D
    public ReservaRespuestaDTO crearReserva(ReservaRegistroDTO reservaDTO) {
    
        //1ero buscamos al user
        Usuario usuario = usuarioRepository.findById(reservaDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado :C"));

        //despues al evento
        Evento evento = eventoRepository.buscarPorIdConBloqueo(reservaDTO.getIdEvento())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado :C"));

        //consultamos reservas, o sea hay reservas disp.?
        Long entradasReservadas = reservaRepository.sumarEntradasReservadas(evento.getIdEvento());
        Long disponibles = evento.getCapacidadTotal() - entradasReservadas;

        //validamos si hay disponibilidad segun cant solicitada
        if (reservaDTO.getCantidadSolicitada() > disponibles) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Lo sentimos, no hay suficientes entradas disponibles para el evento."
            );
        }

        //creamos y guardamos la reserva
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setEvento(evento);
        reserva.setCantidadSolicitada(reservaDTO.getCantidadSolicitada());

        //devolvemos respuesta
        Reserva reservaGuardada = reservaRepository.save(reserva);
        return new ReservaRespuestaDTO(reservaGuardada.getIdReserva(), reservaGuardada.getUsuario().getIdUsuario(), reservaGuardada.getEvento().getIdEvento(), reservaGuardada.getCantidadSolicitada());
    }

    @Override
    public List<ReservaRespuestaDTO> listarReservas() {
        
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream()
                .map(reserva -> new ReservaRespuestaDTO(
                                reserva.getIdReserva(), 
                                reserva.getUsuario().getIdUsuario(), 
                                reserva.getEvento().getIdEvento(), 
                                reserva.getCantidadSolicitada()
                ))
                .toList();        
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReservaRespuestaDTO> obtenerReservaPorId(Long id) {

        return reservaRepository.findById(id)
            .map(reserva -> new ReservaRespuestaDTO(
                            reserva.getIdReserva(), 
                            reserva.getUsuario().getIdUsuario(), 
                            reserva.getEvento().getIdEvento(), 
                            reserva.getCantidadSolicitada()
            ));
    }

}
