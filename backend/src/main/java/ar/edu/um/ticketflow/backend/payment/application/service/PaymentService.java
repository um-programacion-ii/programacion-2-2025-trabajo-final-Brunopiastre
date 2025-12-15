package ar.edu.um.ticketflow.backend.payment.application.service;

import ar.edu.um.ticketflow.backend.payment.domain.Payment;
import ar.edu.um.ticketflow.backend.payment.infrastructure.persistence.entity.PaymentEntity;
import ar.edu.um.ticketflow.backend.payment.infrastructure.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment create(Payment payment) {
        PaymentEntity entity = toEntity(payment);
        PaymentEntity saved = paymentRepository.save(entity);
        return toDomain(saved);
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private PaymentEntity toEntity(Payment payment) {
        return new PaymentEntity(
                payment.getId(),
                payment.getTicketId(),
                payment.getAmount(),
                payment.getStatus()
        );
    }

    private Payment toDomain(PaymentEntity entity) {
        return new Payment(
                entity.getId(),
                entity.getTicketId(),
                entity.getAmount(),
                entity.getStatus()
        );
    }
}
