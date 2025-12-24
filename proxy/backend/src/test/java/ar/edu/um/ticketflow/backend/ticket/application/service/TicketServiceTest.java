package ar.edu.um.ticketflow.backend.ticket.application.service;

import ar.edu.um.ticketflow.backend.ticket.domain.Ticket;
import ar.edu.um.ticketflow.backend.ticket.infrastructure.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void createAndListTickets() {
        // Given
        Ticket toCreate = new Ticket(null, 100L, "buyer@test.com", "CREATED");

        // When
        Ticket created = ticketService.create(toCreate);

        // Then
        assertThat(created.getId()).isNotNull();
        assertThat(created.getEventId()).isEqualTo(100L);
        assertThat(created.getBuyerEmail()).isEqualTo("buyer@test.com");

        List<Ticket> all = ticketService.findAll();
        assertThat(all).isNotEmpty();
        assertThat(all.stream().anyMatch(t -> t.getId().equals(created.getId()))).isTrue();
        // Repository is also wired and points to the same H2 DB
        assertThat(ticketRepository.findAll()).isNotEmpty();
    }
}
