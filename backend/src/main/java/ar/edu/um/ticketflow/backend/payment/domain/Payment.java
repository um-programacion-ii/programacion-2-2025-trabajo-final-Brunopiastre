package ar.edu.um.ticketflow.backend.payment.domain;

import java.math.BigDecimal;

public class Payment {

    private Long id;
    private Long ticketId;
    private BigDecimal amount;
    private String status; // PENDING, APPROVED, REJECTED

    public Payment() {
    }

    public Payment(Long id, Long ticketId, BigDecimal amount, String status) {
        this.id = id;
        this.ticketId = ticketId;
        this.amount = amount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
