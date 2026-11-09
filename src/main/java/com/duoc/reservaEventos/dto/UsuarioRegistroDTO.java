package com.duoc.reservaEventos.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
public class UsuarioRegistroDTO {

    @NotBlank 
    private String nombre;
    
    @Email 
    @NotBlank 
    private String email;

}
