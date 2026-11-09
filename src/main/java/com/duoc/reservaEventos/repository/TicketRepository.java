package com.duoc.reservaEventos.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.duoc.reservaEventos.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
