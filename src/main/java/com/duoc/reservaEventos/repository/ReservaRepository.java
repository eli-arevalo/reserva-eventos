package com.duoc.reservaEventos.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.duoc.reservaEventos.entity.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
    
    @Query (
        "SELECT COALESCE(SUM(r.cantidadSolicitada), 0) " + //sumamos las entradas reservadas y si no hay aun devuelve 0
        "FROM Reserva r " + //aca consultamos la entidad reserva
        "WHERE r.evento.idEvento = :idEvento" //filtramos por el id del evento
    ) 
    Long sumarEntradasReservadas(@Param ("idEvento") Long idEvento);

    
}
