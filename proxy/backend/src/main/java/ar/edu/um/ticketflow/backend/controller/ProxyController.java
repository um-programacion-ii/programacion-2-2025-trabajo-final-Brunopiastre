package ar.edu.um.ticketflow.proxy.controller;

import ar.edu.um.ticketflow.proxy.service.CatedraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

  private final Logger log = LoggerFactory.getLogger(ProxyController.class);
  private final CatedraService catedraService;
  private final RestTemplate restTemplate;

  @Value("${catedra.url}")
  private String catedraUrl;

  @Value("${catedra.token}")
  private String catedraToken;

  public ProxyController(CatedraService catedraService) {
    this.catedraService = catedraService;
    this.restTemplate = new RestTemplate();
  }

  // 1. ENDPOINT PARA CONSULTAR ASIENTOS (Usa el Service de Redis)
  @GetMapping("/eventos/{id}/asientos")
  public ResponseEntity<Map<Object, Object>> obtenerEstadoAsientos(@PathVariable String id) {
    log.info("🔍 Consultando asientos para evento: {}", id);
    Map<Object, Object> asientos = catedraService.obtenerAsientosDeEvento(id);
    return ResponseEntity.ok(asientos);
  }

  // 2. ENDPOINT PARA REENVIAR VENTAS (Usa RestTemplate hacia la Cátedra)
  @PostMapping("/ventas")
  public ResponseEntity<String> procesarVenta(@RequestBody Object ventaDto) {
    try {
      log.info("🚀 Reenviando venta a la API de la Cátedra...");

      String urlCatedra = catedraUrl + "/ventas";

      HttpHeaders headers = new HttpHeaders();
      headers.setBearerAuth(catedraToken); // El Proxy pone el TOKEN
      headers.setContentType(MediaType.APPLICATION_JSON);

      HttpEntity<Object> request = new HttpEntity<>(ventaDto, headers);

      return restTemplate.postForEntity(urlCatedra, request, String.class);

    } catch (Exception e) {
      log.error("❌ Error al contactar a la cátedra: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
        .body("Cátedra no disponible: " + e.getMessage());
    }
  }
}
