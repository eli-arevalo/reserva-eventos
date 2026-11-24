package com.duoc.reservaEventos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());//exige que cualquier petición http que llegu, debe estar auth, sino 401
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));//configura la app como un resource server de OAuth2 que valida los tokens jwt, así spring va a buscar auto. la config. de application properties apara verificar la firmay vigencia del token >:D
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//esto le ordena a spring ecurity que no cree ni use sesiones http, o sea cada peticion con su token
        http.csrf(csrf -> csrf.disable());//desactivamos csrf, y es seguro debido a que no usamos cookies de sesión y autenticamos con jwt por lo que no somo s vulnerables >:) (a este ataque) 

        return http.build();
    }
}
