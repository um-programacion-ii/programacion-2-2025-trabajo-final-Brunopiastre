package ar.edu.um.ticketflow.backend.common.kafka;

import ar.edu.um.ticketflow.backend.event.application.service.EventoService;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.in.web.dto.CatedraEventDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

  private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

  private final EventoService eventoService;
  private final ObjectMapper objectMapper;

  public KafkaConsumerService(EventoService eventoService, ObjectMapper objectMapper) {
    this.eventoService = eventoService;
    this.objectMapper = objectMapper;
  }

  // Se actualizó el topic a "events" para coincidir con tu KafkaConfig
  @KafkaListener(topics = "eventos-actualizacion", groupId = "Bruno")
  public void listen(String message) {
    log.info(":inbox_tray: Kafka: Mensaje crudo recibido: {}", message);

    try {
      // Convertimos el mensaje que llega de Kafka a un DTO
      CatedraEventDto dto = objectMapper.readValue(message, CatedraEventDto.class);

      // Guardamos o actualizamos en la DB
      eventoService.actualizarOcrearEvento(dto);

      log.info(":white_check_mark: Kafka: Evento procesado y guardado exitosamente: ID={}, Nombre={}",
        dto.getId(), dto.getNombre());

    } catch (JsonProcessingException e) {
      log.error(":x: Kafka: Error al deserializar el JSON. Mensaje inválido: {}", message, e);
    } catch (Exception e) {
      log.error(":x: Kafka: Error inesperado al procesar el evento", e);
    }
  }
}
