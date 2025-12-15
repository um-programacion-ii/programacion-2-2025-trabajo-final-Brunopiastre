package ar.edu.um.ticketflow.backend.ticket.application.service;

import ar.edu.um.ticketflow.backend.ticket.domain.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TicketNotificationService {
    private static final Logger log = LoggerFactory.getLogger(TicketNotificationService.class);

    public void notifyTicketCreated(Ticket ticket) {
        if (ticket == null) {
            return;
        }
        log.info("Ticket created notification - id={}, eventId={}, buyer={}",
                ticket.getId(), ticket.getEventId(), ticket.getBuyerEmail());
    }
}
