package ar.edu.um.ticketflow.backend.ticket.domain;

public class Ticket {

    private Long id;
    private Long eventId;
    private String buyerEmail;
    private String status;

    public Ticket() {
    }

    public Ticket(Long id, Long eventId, String buyerEmail, String status) {
        this.id = id;
        this.eventId = eventId;
        this.buyerEmail = buyerEmail;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
