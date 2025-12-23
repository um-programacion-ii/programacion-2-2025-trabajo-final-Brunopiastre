package ar.edu.um.ticketflow.backend.payment.infrastructure.repository;

import ar.edu.um.ticketflow.backend.payment.infrastructure.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
