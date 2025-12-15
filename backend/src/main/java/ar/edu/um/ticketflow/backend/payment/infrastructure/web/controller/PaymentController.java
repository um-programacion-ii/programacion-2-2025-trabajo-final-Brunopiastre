package ar.edu.um.ticketflow.backend.payment.infrastructure.web.controller;

import ar.edu.um.ticketflow.backend.payment.application.service.PaymentService;
import ar.edu.um.ticketflow.backend.payment.domain.Payment;
import ar.edu.um.ticketflow.backend.payment.infrastructure.web.dto.PaymentResultDto;
import ar.edu.um.ticketflow.backend.payment.infrastructure.web.dto.PaymentRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResultDto create(@RequestBody PaymentRequestDto request) {
        Payment payment = new Payment(
                null,
                request.getTicketId(),
                request.getAmount(),
                "PENDING"
        );
        Payment created = paymentService.create(payment);
        return toDto(created);
    }

    @GetMapping
    public List<PaymentResultDto> getAll() {
        return paymentService.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private PaymentResultDto toDto(Payment payment) {
        return new PaymentResultDto(
                payment.getId(),
                payment.getTicketId(),
                payment.getAmount(),
                payment.getStatus()
        );
    }
}
