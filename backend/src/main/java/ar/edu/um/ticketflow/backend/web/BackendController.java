package ar.edu.um.ticketflow.backend.web;

import ar.edu.um.ticketflow.backend.config.BackendProperties;
import ar.edu.um.ticketflow.backend.service.BackendProxyClient;
import ar.edu.um.ticketflow.backend.service.EventCacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BackendController {

    private final BackendProxyClient proxyClient;
    private final EventCacheService cacheService;
    private final BackendProperties properties;

    public BackendController(BackendProxyClient proxyClient, EventCacheService cacheService, BackendProperties properties) {
        this.proxyClient = proxyClient;
        this.cacheService = cacheService;
        this.properties = properties;
    }

    // Endpoint interno para recibir notificación del proxy (a su vez, originada por Kafka)
    @PostMapping("/internal/proxy/eventos/updated")
    public Mono<ResponseEntity<Map<String, Object>>> proxyNotificacion(
            @RequestHeader(value = "X-Internal-Token", required = false) String token,
            @RequestBody(required = false) Map<String, Object> payload) {
        if (token == null || !token.equals(properties.getServiceToken())) {
            return Mono.just(ResponseEntity.status(401).build());
        }
        String eventId = null;
        if (payload != null) {
            Object val = payload.get("eventId");
            if (val != null) eventId = String.valueOf(val);
            if (eventId == null && payload.get("value") != null) {
                // try to parse a simple JSON-like string to find eventId; if not, ignore
                String valueStr = String.valueOf(payload.get("value"));
                // naive extraction: looks for "eventId":"123"
                int idx = valueStr.indexOf("eventId");
                if (idx >= 0) {
                    int colon = valueStr.indexOf(':', idx);
                    if (colon > 0) {
                        int startQuote = valueStr.indexOf('"', colon);
                        int endQuote = valueStr.indexOf('"', startQuote + 1);
                        if (startQuote >= 0 && endQuote > startQuote) {
                            eventId = valueStr.substring(startQuote + 1, endQuote);
                        }
                    }
                }
            }
        }
        if (eventId == null) {
            Map<String, Object> resp = new HashMap<>();
            resp.put("status", "accepted");
            resp.put("detail", "no eventId provided");
            return Mono.just(ResponseEntity.accepted().body(resp));
        }
        String finalEventId = eventId;
        return proxyClient.getEstadoEvento(eventId)
                .map(map -> {
                    Object estado = map.get("estado");
                    cacheService.updateEstadoRaw(finalEventId, estado != null ? String.valueOf(estado) : null);
                    Map<String, Object> resp = new HashMap<>();
                    resp.put("status", "updated");
                    resp.put("eventId", finalEventId);
                    return ResponseEntity.ok(resp);
                });
    }

    // Endpoint público/interno para forzar actualización rápida
    @PostMapping("/sync/eventos")
    public Mono<ResponseEntity<Void>> syncEventos() {
        return proxyClient.forzarActualizacion().then(Mono.just(ResponseEntity.accepted().build()));
    }

    // Obtener asientos y su disponibilidad (combina la grilla con estado; aquí devolvemos el estado crudo como mínimo)
    @GetMapping("/eventos/{id}/asientos")
    public Mono<ResponseEntity<Map<String, Object>>> obtenerAsientos(@PathVariable("id") String eventoId) {
        return proxyClient.getEstadoEvento(eventoId)
                .map(map -> ResponseEntity.ok(map));
    }

    // Bloquear un asiento por 5 minutos por defecto
    @PostMapping("/eventos/{id}/bloquear-asiento")
    public Mono<Map> bloquearAsiento(@PathVariable("id") String eventoId, @RequestBody Map<String, Object> body) {
        return proxyClient.bloquearAsiento(eventoId, body);
    }
}
