package ar.edu.um.ticketflow.backend.sale.infrastructure.adapter.in.web;

import ar.edu.um.ticketflow.backend.sale.application.service.SaleService;
import ar.edu.um.ticketflow.backend.sale.infrastructure.adapter.out.jpa.entity.SaleEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales") // Endpoint para el Frontend
public class SaleController {

  private final SaleService saleService;

  public SaleController(SaleService saleService) {
    this.saleService = saleService;
  }

  @PostMapping("/checkout")
  public ResponseEntity<SaleEntity> checkout(@RequestBody SaleRequest request) {
    // Obtenemos el email del usuario que está logueado actualmente
    String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

    // Iniciamos la venta (que la guarda como PENDIENTE y trata de avisar al Proxy)
    SaleEntity sale = saleService.iniciarVenta(
      userEmail,
      request.getEventId(),
      request.getSeats(),
      request.getTotalPrice()
    );

    return ResponseEntity.ok(sale);
  }
}

// Clase DTO simple para recibir los datos del JSON
class SaleRequest {
  private Long eventId;
  private String seats;
  private Double totalPrice;

  // Getters y Setters
  public Long getEventId() { return eventId; }
  public void setEventId(Long eventId) { this.eventId = eventId; }
  public String getSeats() { return seats; }
  public void setSeats(String seats) { this.seats = seats; }
  public Double getTotalPrice() { return totalPrice; }
  public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
}
