package ar.edu.um.ticketflow.backend.common.kafka;

import ar.edu.um.ticketflow.backend.event.application.service.EventoService;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.in.web.dto.CatedraEventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

  private final EventoService eventoService;
  private final ObjectMapper objectMapper;

  public KafkaConsumerService(EventoService eventoService, ObjectMapper objectMapper) {
    this.eventoService = eventoService;
    this.objectMapper = objectMapper;
  }

  @KafkaListener(topics = "eventos-topic", groupId = "ticketflow-group")
  public void listen(String message) {
    try {
      // Convertimos el mensaje que llega de Kafka a un DTO
      CatedraEventDto dto = objectMapper.readValue(message, CatedraEventDto.class);

      // Usamos el método que ya tenías para guardarlo en la DB
      eventoService.actualizarOcrearEvento(dto);

      System.out.println("📥 Kafka: Evento recibido y guardado: " + dto.getNombre());
    } catch (Exception e) {
      System.err.println("❌ Error procesando mensaje de Kafka: " + e.getMessage());
    }
  }
}
