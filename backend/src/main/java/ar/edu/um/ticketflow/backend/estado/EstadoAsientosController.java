package ar.edu.um.ticketflow.backend.estado;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backend/eventos")
public class EstadoAsientosController {

    private final EstadoAsientosService service;

    public EstadoAsientosController(EstadoAsientosService service) {
        this.service = service;
    }

    @GetMapping("/{eventoId}/asientos")
    public ResponseEntity<List<EstadoAsientoDto>> obtenerEstado(@PathVariable long eventoId) {
        return ResponseEntity.ok(service.obtenerEstadoAsientos(eventoId));
    }
}
