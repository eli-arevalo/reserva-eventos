package com.duoc.reservaEventos.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 
public class Evento {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    @NotBlank 
    private String nombreEvento;

    @NotNull
    @Future 
    private LocalDateTime fechaEvento;

    @NotNull 
    @Positive
    private BigDecimal precioEntrada;

    @NotNull
    @Positive
    private Integer capacidadTotal;
}
