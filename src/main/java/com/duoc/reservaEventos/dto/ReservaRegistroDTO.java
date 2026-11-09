package com.duoc.reservaEventos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
public class ReservaRegistroDTO {

    @NotNull 
    private  Long idUsuario;

    @NotNull 
    private Long  idEvento;

    @NotNull 
    @Positive 
    private  Integer cantidadSolicitada;
}
