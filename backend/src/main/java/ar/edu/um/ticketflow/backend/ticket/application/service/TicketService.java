package ar.edu.um.ticketflow.backend.ticket.application.service;

import ar.edu.um.ticketflow.backend.ticket.domain.Ticket;
import ar.edu.um.ticketflow.backend.ticket.infrastructure.persistence.entity.TicketEntity;
import ar.edu.um.ticketflow.backend.ticket.infrastructure.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket create(Ticket ticket) {
        TicketEntity entity = toEntity(ticket);
        TicketEntity saved = ticketRepository.save(entity);
        return toDomain(saved);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private TicketEntity toEntity(Ticket ticket) {
        return new TicketEntity(
                ticket.getId(),
                ticket.getEventId(),
                ticket.getBuyerEmail(),
                ticket.getStatus()
        );
    }

    private Ticket toDomain(TicketEntity entity) {
        return new Ticket(
                entity.getId(),
                entity.getEventId(),
                entity.getBuyerEmail(),
                entity.getStatus()
        );
    }
}
