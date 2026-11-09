package com.duoc.reservaEventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.duoc.reservaEventos.entity.Evento;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.Optional;

public interface EventoRepository  extends JpaRepository<Evento, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)//es un bloqueo pesimista para evitar que otros hilos lean o escriban mientras se realiza la operación
    @Query("SELECT e FROM Evento e WHERE e.idEvento = :id")
    Optional<Evento> buscarPorIdConBloqueo(@Param("id") Long id);
}
