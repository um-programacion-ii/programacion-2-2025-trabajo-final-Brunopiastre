package ar.edu.um.ticketflow.proxy.web;

import ar.edu.um.ticketflow.proxy.service.CatedraClientService;
import ar.edu.um.ticketflow.proxy.service.RedisSeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/proxy")
public class ProxyController {
    private final RedisSeatService redisSeatService;
    private final CatedraClientService catedraClientService;

    public ProxyController(RedisSeatService redisSeatService, CatedraClientService catedraClientService) {
        this.redisSeatService = redisSeatService;
        this.catedraClientService = catedraClientService;
    }

    @GetMapping("/eventos/{id}/estado")
    public ResponseEntity<Map<String, Object>> getEstadoEvento(@PathVariable("id") String eventoId) {
        String raw = redisSeatService.getEventStateRaw(eventoId);
        Map<String, Object> resp = new HashMap<>();
        resp.put("eventoId", eventoId);
        resp.put("estado", raw); // raw JSON or null from Redis
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/eventos/{id}/bloquear-asiento")
    public Mono<Map> bloquearAsiento(@PathVariable("id") String eventoId, @RequestBody Map<String, Object> body) {
        body.put("eventoId", eventoId);
        return catedraClientService.bloquearAsiento(eventoId, body);
    }

    @PostMapping("/forzar-actualizacion")
    public Mono<ResponseEntity<Void>> forzarActualizacion() {
        return catedraClientService.forzarActualizacion().then(Mono.just(ResponseEntity.accepted().build()));
    }
}
