package ar.edu.um.ticketflow.backend.ticket.infrastructure.web.controller;

import ar.edu.um.ticketflow.backend.ticket.application.service.TicketService;
import ar.edu.um.ticketflow.backend.ticket.domain.Ticket;
import ar.edu.um.ticketflow.backend.ticket.infrastructure.web.dto.TicketOverviewDto;
import ar.edu.um.ticketflow.backend.ticket.infrastructure.web.dto.TicketDetailDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDetailDto create(@RequestBody TicketOverviewDto request) {
        Ticket ticket = new Ticket(
                null,
                request.getEventId(),
                request.getBuyerEmail(),
                "CREATED"
        );
        Ticket created = ticketService.create(ticket);
        return toDto(created);
    }

    @GetMapping
    public List<TicketDetailDto> getAll() {
        return ticketService.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private TicketDetailDto toDto(Ticket ticket) {
        return new TicketDetailDto(
                ticket.getId(),
                ticket.getEventId(),
                ticket.getBuyerEmail(),
                ticket.getStatus()
        );
    }
}
