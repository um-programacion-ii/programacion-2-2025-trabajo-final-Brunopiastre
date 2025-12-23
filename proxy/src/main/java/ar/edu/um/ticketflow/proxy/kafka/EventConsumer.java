package ar.edu.um.ticketflow.proxy.kafka;

import ar.edu.um.ticketflow.proxy.config.BackendProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class EventConsumer {

    private final WebClient client;

    public EventConsumer(BackendProperties backendProperties) {
        this.client = WebClient.builder()
                .baseUrl(backendProperties.getBaseUrl())
                .build();
    }

    @KafkaListener(topics = "eventos-cambios", groupId = "ticketflow")
    public void onMessage(String mensaje) {
        System.out.println("[KAFKA] Mensaje recibido: " + mensaje);

        client.post()
                .uri("/api/backend/internal/notificaciones/kafka")
                .bodyValue(mensaje)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }
}
