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
import com.duoc.reservaEventos.pdf.TicketPdfService;
import com.duoc.reservaEventos.entity.Reserva;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service 
public class ReservaServiceImpl implements ReservaService {
    private final ReservaRepository reservaRepository;
    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TicketPdfService ticketPdfService;


    public ReservaServiceImpl(ReservaRepository reservaRepository, EventoRepository eventoRepository, UsuarioRepository usuarioRepository, TicketPdfService ticketPdfService) {
        this.reservaRepository = reservaRepository;
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
        this.ticketPdfService = ticketPdfService;
    }

    @Override
    @Transactional
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

        BigDecimal precioUnitario = evento.getPrecioEntrada();

        BigDecimal precioTotal = precioUnitario.multiply(
            BigDecimal.valueOf(reservaDTO.getCantidadSolicitada())
        );

        //creamos y guardamos la reserva
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setEvento(evento);
        reserva.setCantidadSolicitada(reservaDTO.getCantidadSolicitada());
        reserva.setPrecioUnitario(precioUnitario);
        reserva.setPrecioTotal(precioTotal);

        //devolvemos respuesta
        Reserva reservaGuardada = reservaRepository.save(reserva);
        return new ReservaRespuestaDTO(reservaGuardada.getIdReserva(),
                                    reservaGuardada.getUsuario().getIdUsuario(),
                                    reservaGuardada.getEvento().getIdEvento(),
                                    reservaGuardada.getCantidadSolicitada(),
                                    reservaGuardada.getEvento().getNombreEvento(),
                                    reservaGuardada.getPrecioUnitario(),
                                    reservaGuardada.getPrecioTotal()
        );
    }

    @Override
    public List<ReservaRespuestaDTO> listarReservas() {
        
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream()
                .map(reserva -> new ReservaRespuestaDTO(
                                reserva.getIdReserva(), 
                                reserva.getUsuario().getIdUsuario(), 
                                reserva.getEvento().getIdEvento(), 
                                reserva.getCantidadSolicitada(),
                                reserva.getEvento().getNombreEvento(),
                                reserva.getPrecioUnitario(),
                                reserva.getPrecioTotal()

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
                            reserva.getCantidadSolicitada(),
                            reserva.getEvento().getNombreEvento(),
                            reserva.getPrecioUnitario(),
                            reserva.getPrecioTotal()
            ));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] generarPdf(Long idReserva) throws IOException{

        Reserva reserva = reservaRepository.findById(idReserva)
            .orElseThrow(()-> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Reserva no encontrada :C"
            ));
        return ticketPdfService.generarPdf(reserva);
    }
}
