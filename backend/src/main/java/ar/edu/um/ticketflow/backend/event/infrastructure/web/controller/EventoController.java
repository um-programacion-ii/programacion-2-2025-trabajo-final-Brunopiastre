package ar.edu.um.ticketflow.backend.event.infrastructure.web.controller;

import ar.edu.um.ticketflow.backend.domain.Evento;
import ar.edu.um.ticketflow.backend.domain.port.in.EventoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST hexagonal para Evento.
 * Depende únicamente del puerto de entrada EventoUseCase.
 */
@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoUseCase eventoUseCase;

    public EventoController(EventoUseCase eventoUseCase) {
        this.eventoUseCase = eventoUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Evento>> listar() {
        return ResponseEntity.ok(eventoUseCase.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Long id) {
        return eventoUseCase.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/externo/{idCatedra}")
    public ResponseEntity<Evento> buscarPorIdCatedra(@PathVariable String idCatedra) {
        return eventoUseCase.buscarPorIdCatedra(idCatedra)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Evento> crear(@RequestBody Evento evento) {
        Evento creado = eventoUseCase.guardar(evento);
        return ResponseEntity.ok(creado);
    }
}
