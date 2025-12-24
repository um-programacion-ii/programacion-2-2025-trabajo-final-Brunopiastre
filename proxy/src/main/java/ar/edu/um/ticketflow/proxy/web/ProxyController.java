package ar.edu.um.ticketflow.proxy.web;

import ar.edu.um.ticketflow.proxy.service.RedisSeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

    private final RedisSeatService seatService;

    public ProxyController(RedisSeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/asientos/{eventoId}")
    public ResponseEntity<String> getAsientos(@PathVariable Long eventoId) {
        String mapa = seatService.obtenerAsientos(eventoId);
        if (mapa == null) {
            // Si no esta, devolvemos 404
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapa);
    }
    
    @GetMapping("/ping")
    public String ping() {
        return "PONG: Proxy activo y escuchando en puerto 8081";
    }
}
