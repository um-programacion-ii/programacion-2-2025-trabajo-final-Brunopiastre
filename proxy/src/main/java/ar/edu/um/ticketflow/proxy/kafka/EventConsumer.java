package ar.edu.um.ticketflow.proxy.kafka;

import ar.edu.um.ticketflow.proxy.config.BackendProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class EventConsumer {

  private final WebClient client;
  private final BackendProperties backendProperties; // Guarda las propiedades para usar el token

  public EventConsumer(BackendProperties backendProperties) {
    this.backendProperties = backendProperties;
    this.client = WebClient.builder()
      .baseUrl(backendProperties.getBaseUrl())
      // Opción A: Configurar el header por defecto aquí
      .defaultHeader("Authorization", "Bearer " + backendProperties.getInternalToken())
      .build();
  }

  @KafkaListener(topics = "eventos-cambios", groupId = "ticketflow")
  public void onMessage(String mensaje) {
    System.out.println("[KAFKA] Mensaje recibido: " + mensaje);

    client.post()
      .uri("/api/backend/internal/notificaciones/kafka")
      // Si usaste la Opción A, no necesitas hacer nada más aquí.
      // Si no, debes agregar .header("Authorization", "Bearer " + backendProperties.getInternalToken())
      .bodyValue(mensaje)
      .retrieve()
      .bodyToMono(Void.class)
      .subscribe(
        null,
        error -> System.err.println("Error enviando al backend: " + error.getMessage()) // Agrega logs de error
      );
  }
}
