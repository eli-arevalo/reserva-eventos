package com.duoc.reservaEventos.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.duoc.reservaEventos.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
}
