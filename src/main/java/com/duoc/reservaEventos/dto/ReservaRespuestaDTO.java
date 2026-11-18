package com.duoc.reservaEventos.dto;

import java.math.BigDecimal;

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
    private String nombreEvento;
    private BigDecimal precioUnitario;
    private BigDecimal precioTotal;

}
