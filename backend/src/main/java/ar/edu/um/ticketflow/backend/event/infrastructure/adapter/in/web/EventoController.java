package ar.edu.um.ticketflow.backend.event.infrastructure.adapter.in.web;

import ar.edu.um.ticketflow.backend.application.usecase.EventoUseCaseService;
import ar.edu.um.ticketflow.backend.domain.Evento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

  private final EventoUseCaseService eventoService;

  public EventoController(EventoUseCaseService eventoService) {
    this.eventoService = eventoService;
  }

  @GetMapping
  public ResponseEntity<List<Evento>> listarEventos() {
    // Llama a tu servicio, que llama al puerto, que llama al adaptador, que busca en la BD
    return ResponseEntity.ok(eventoService.listarTodos());
  }

  @PostMapping
  public ResponseEntity<Evento> crearEvento(@RequestBody Evento evento) {
    // Llama a tu servicio para guardar
    return ResponseEntity.ok(eventoService.guardar(evento));
  }
}
