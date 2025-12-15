package ar.edu.um.ticketflow.backend.payment.application.service;

import ar.edu.um.ticketflow.backend.payment.domain.Payment;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PaymentSyncService {

    public Payment syncStatus(Payment payment, String newStatus) {
        if (payment == null) {
            throw new IllegalArgumentException("payment is null");
        }
        if (newStatus == null || newStatus.isBlank()) {
            return payment;
        }
        if (!Objects.equals(payment.getStatus(), newStatus)) {
            payment.setStatus(newStatus);
        }
        return payment;
    }
}
