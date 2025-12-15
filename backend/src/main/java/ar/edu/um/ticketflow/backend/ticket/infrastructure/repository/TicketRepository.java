package ar.edu.um.ticketflow.backend.ticket.infrastructure.repository;

import ar.edu.um.ticketflow.backend.ticket.infrastructure.persistence.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
}
