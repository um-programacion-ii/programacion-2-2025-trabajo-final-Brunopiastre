package ar.edu.um.ticketflow.backend.event.infrastructure.adapter.in.web;

import ar.edu.um.ticketflow.backend.event.application.service.EventoService;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.in.web.dto.CatedraEventDto;
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

  public InternalNotificationController(EventoService eventoService, AuthService authService) {
    this.eventoService = eventoService;
    this.authService = authService;
    this.restTemplate = new RestTemplate();
  }

  @PostMapping("/notificacion-evento")
  public ResponseEntity<Void> recibirNotificacion(@RequestBody String idEvento) {
    try {
      // Limpiamos el ID por si viene con comillas o espacios
      String idLimpio = idEvento.replace("\"", "").trim();

      // 1. Consultamos a la cátedra el dato fresco
      String url = authService.getUrl() + "/" + idLimpio;
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
}
