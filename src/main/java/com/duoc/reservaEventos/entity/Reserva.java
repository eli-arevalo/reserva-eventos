package com.duoc.reservaEventos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 
public class Reserva {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    @NotNull 
    @Positive 
    private Integer cantidadSolicitada;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;
}

