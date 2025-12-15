package ar.edu.um.ticketflow.backend.payment.infrastructure.web.dto;

import java.math.BigDecimal;

public class PaymentRequestDto {

    private Long ticketId;
    private BigDecimal amount;

    public PaymentRequestDto() {
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
}
