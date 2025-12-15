package ar.edu.um.ticketflow.backend.event.infrastructure.web.controller;

import ar.edu.um.ticketflow.backend.event.application.service.SincronizacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/sync")
public class SyncController {

    private final SincronizacionService sincronizacionService;

    public SyncController(SincronizacionService sincronizacionService) {
        this.sincronizacionService = sincronizacionService;
    }

    @PostMapping("/eventos")
    public ResponseEntity<Map<String, Object>> syncEventos() {
        int count = sincronizacionService.sincronizarEventos();
        return ResponseEntity.ok(Map.of(
                "procesados", count,
                "status", "ok"
        ));
    }
}
