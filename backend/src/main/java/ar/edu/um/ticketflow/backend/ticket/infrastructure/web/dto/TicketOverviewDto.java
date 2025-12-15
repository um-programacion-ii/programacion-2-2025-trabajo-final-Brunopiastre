package ar.edu.um.ticketflow.backend.ticket.infrastructure.web.dto;

public class TicketOverviewDto {

    private Long eventId;
    private String buyerEmail;

    public TicketOverviewDto() {
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }
}
