package com.duoc.reservaEventos.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ticket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;

    private String claveS3;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva; 
}
