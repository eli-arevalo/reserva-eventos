package com.duoc.reservaEventos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor 
public class ReservaRespuestaDTO {

    private Long idReserva;
    private Long idUsuario;
    private Long idEvento;
    private Integer cantidadSolicitada;

}
