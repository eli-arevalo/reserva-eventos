package com.duoc.reservaEventos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 
public class Usuario {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotBlank 
    private String nombre;

    @Column (unique = true)
    @NotBlank 
    @Email 
    private String email;
    private String passwordHash;

}
