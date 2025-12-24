package ar.edu.um.ticketflow.backend.event.infrastructure.adapter.in.web;

import ar.edu.um.ticketflow.backend.event.application.service.EventoService;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.out.web.dto.CatedraEventDto;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.out.client.CatedraClient;
import ar.edu.um.ticketflow.backend.auth.AuthService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/internal")
public class InternalNotificationController {

  private final EventoService eventoService;
  private final AuthService authService;
  private final RestTemplate restTemplate;
  private final CatedraClient catedraClient;

  public InternalNotificationController(EventoService eventoService, AuthService authService, CatedraClient catedraClient) {
    this.eventoService = eventoService;
    this.authService = authService;
    this.restTemplate = new RestTemplate();
    this.catedraClient = catedraClient;
  }

  @PostMapping("/notificacion-evento")
  public ResponseEntity<Void> recibirNotificacion(@RequestBody String idEvento) {
    try {
      // Limpiamos el ID por si viene con comillas o espacios
      String idLimpio = idEvento.replace("\"", "").trim();

      // 1. Consultamos a la cátedra el dato fresco
      String url = authService.getUrl() + "/api/endpoints/v1/evento/" + idLimpio;
      HttpHeaders headers = new HttpHeaders();
      headers.setBearerAuth(authService.getToken());

      ResponseEntity<CatedraEventDto> response = restTemplate.exchange(
        url, HttpMethod.GET, new HttpEntity<>(headers), CatedraEventDto.class);

      if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
        // 2. Usamos el service para impactar la BD
        eventoService.actualizarOcrearEvento(response.getBody());
      }
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      System.err.println("Error en notificación interna: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // Endpoint manual para forzar sincronización completa y ver diagnóstico
  @GetMapping("/force-sync")
  public ResponseEntity<String> forceSync() {
    StringBuilder sb = new StringBuilder();
    sb.append("[ForceSync] Iniciando sincronización manual...\n");
    try {
      var eventos = catedraClient.fetchEvents();
      sb.append("Eventos obtenidos de cátedra: ").append(eventos.size()).append("\n");
      int ok = 0;
      for (CatedraEventDto dto : eventos) {
        try {
          eventoService.actualizarOcrearEvento(dto);
          ok++;
        } catch (Exception e) {
          sb.append("Error guardando evento id=").append(dto.getId()).append(": ")
            .append(e.getMessage()).append("\n");
        }
      }
      sb.append("Eventos persistidos: ").append(ok).append("\n");
      return ResponseEntity.ok(sb.toString());
    } catch (Exception e) {
      sb.append("Fallo general: ").append(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sb.toString());
    }
  }
}
