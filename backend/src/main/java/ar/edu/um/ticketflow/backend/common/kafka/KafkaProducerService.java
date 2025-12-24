package ar.edu.um.ticketflow.backend.common.kafka;

import org.springframework.stereotype.Service;
// import org.springframework.kafka.core.KafkaTemplate; // <--- Comentado

@Service
public class KafkaProducerService {

  // --- COMENTAMOS LA DEPENDENCIA REAL ---
  // private final KafkaTemplate<String, String> kafkaTemplate;

  // public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
  //     this.kafkaTemplate = kafkaTemplate;
  // }
  // --------------------------------------

  // Constructor vacío para que Spring no se queje
  public KafkaProducerService() {
  }

  public void sendMessage(String topic, String message) {
    // --- COMENTAMOS EL ENVÍO REAL ---
    // kafkaTemplate.send(topic, message);

    // Solo imprimimos en consola para saber que "hubiera" enviado algo
    System.out.println(">>> [KAFKA FALSO] Enviando a tópico '" + topic + "': " + message);
  }
}
