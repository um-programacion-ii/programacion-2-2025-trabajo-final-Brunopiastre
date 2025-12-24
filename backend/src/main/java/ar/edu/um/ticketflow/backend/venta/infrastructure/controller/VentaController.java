package ar.edu.um.ticketflow.backend.venta.infrastructure.controller;

import ar.edu.um.ticketflow.backend.venta.application.service.VentaService;
import ar.edu.um.ticketflow.backend.venta.infrastructure.controller.dto.CompraRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

  @Autowired
  private VentaService ventaService;

  @PostMapping("/comprar")
  public ResponseEntity<String> comprarEntrada(@RequestBody CompraRequestDto compraRequest) {
    try {
      // AQUI ESTABA EL ERROR: Ahora pasamos el objeto completo DTO
      ventaService.procesarCompra(compraRequest);
      return ResponseEntity.ok("✅ Compra realizada con éxito");
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body("⛔ Error en la compra: " + e.getMessage());
    }
  }
}
