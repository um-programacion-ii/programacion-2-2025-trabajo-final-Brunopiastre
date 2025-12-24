package ar.edu.um.ticketflow.backend.common.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String topic, String message) {
    // Ahora sí enviamos de verdad
    kafkaTemplate.send(topic, message);
    System.out.println(">>> [KAFKA REAL] Enviado a '" + topic + "': " + message);
  }
}
