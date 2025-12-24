package ar.edu.um.ticketflow.backend.event.infrastructure.adapter.in.web;

import ar.edu.um.ticketflow.backend.event.application.service.EventoService;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.out.jpa.entity.EventEntity;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.out.web.dto.SeatDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events") // Mantenemos tu ruta en inglés
public class EventoController {

  private final EventoService eventoService;

  public EventoController(EventoService eventoService) {
    this.eventoService = eventoService;
  }

  @GetMapping
  public ResponseEntity<List<EventEntity>> getAllEvents() {
    return ResponseEntity.ok(eventoService.getAllEvents());
  }

  @GetMapping("/{id}")
  public ResponseEntity<EventEntity> getEventById(@PathVariable Long id) {
    return eventoService.getEventById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/{id}/seats")
  public ResponseEntity<List<SeatDto>> getSeats(@PathVariable Long id) {
    // Nota: Confirma que tu servicio tenga este método 'getSeatsForEvent'
    // Si te da error, cámbialo por 'getSeats(id)' según como lo tengas en el Service
    return ResponseEntity.ok(eventoService.getSeatsForEvent(id));
  }
}
