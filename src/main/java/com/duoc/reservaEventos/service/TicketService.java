package com.duoc.reservaEventos.service;

import com.duoc.reservaEventos.dto.TicketRegistroDTO;
import com.duoc.reservaEventos.dto.TicketRespuestaDTO;
import java.util.Optional;
import java.util.List;

public interface TicketService {
    TicketRespuestaDTO generarTicket(TicketRegistroDTO ticketRegistroDTO);
    List<TicketRespuestaDTO> listarTickets();
    Optional<TicketRespuestaDTO> obtenerTicketPorId(Long id);
}
