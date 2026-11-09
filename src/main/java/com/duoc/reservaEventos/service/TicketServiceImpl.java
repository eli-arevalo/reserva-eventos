package com.duoc.reservaEventos.service;

import org.springframework.stereotype.Service;
import com.duoc.reservaEventos.repository.TicketRepository;
import com.duoc.reservaEventos.dto.TicketRegistroDTO;
import com.duoc.reservaEventos.dto.TicketRespuestaDTO;
import com.duoc.reservaEventos.repository.ReservaRepository;

import java.util.List;
import java.util.Optional;

@Service 
public class TicketServiceImpl implements TicketService{

    private final ReservaRepository reservaRepository;
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, ReservaRepository reservaRepository){
        this.ticketRepository = ticketRepository;
        this.reservaRepository = reservaRepository;
    }

    @Override 
    public TicketRespuestaDTO generarTicket(TicketRegistroDTO ticketRegistroDTO){
        return null;
    }

    @Override 
    public List<TicketRespuestaDTO> listarTickets(){
        return null;
    }

    @Override 
    public Optional<TicketRespuestaDTO> obtenerTicketPorId(Long id){
        return null;
    }

}
