package com.duoc.reservaEventos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class TicketRespuestaDTO {

    private Long idTicket;
    private Long idReserva;
    private String claveS3;
}
