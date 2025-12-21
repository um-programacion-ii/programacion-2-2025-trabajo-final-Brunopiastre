package ar.edu.um.ticketflow.backend.event.infrastructure.adapter.in.scheduler;

import ar.edu.um.ticketflow.backend.application.usecase.EventoUseCaseService;
import ar.edu.um.ticketflow.backend.domain.Evento;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

@Component
public class EventSyncScheduler {

  private final EventoUseCaseService eventoService;
  private final RestClient restClient;
  private final Logger log = LoggerFactory.getLogger(EventSyncScheduler.class);

  public EventSyncScheduler(EventoUseCaseService eventoService) {
    this.eventoService = eventoService;
    // Aquí configuramos el cliente HTTP para llamar a la cátedra
    this.restClient = RestClient.builder()
      .baseUrl("http://URL_DE_LA_CATEDRA_AQUI") // <--- ¡AQUÍ VA LA URL REAL!
      .build();
  }

  // Se ejecuta cada 60000 milisegundos (1 minuto)
  // initialDelay = 5000 significa que espera 5 segs al arrancar antes de la primera vez
  @Scheduled(fixedRate = 60000, initialDelay = 5000)
  public void sincronizarEventos() {
    log.info("🤖 Iniciando sincronización con la API externa...");

    try {
      // 1. Llamamos a la API externa (GET)
      // Asumimos que la API devuelve una lista de objetos que se parecen a nuestro Evento
      List<Evento> eventosExternos = restClient.get()
        .uri("/eventos") // El endpoint específico (ej: /api/eventos)
        .retrieve()
        .body(new ParameterizedTypeReference<List<Evento>>() {});

      if (eventosExternos != null) {
        log.info("Se encontraron {} eventos externos via API.", eventosExternos.size());

        // 2. Guardamos cada evento en nuestra base de datos local
        for (Evento evento : eventosExternos) {
          // Aquí podrías agregar lógica para ver si ya existe y actualizarlo
          // Por ahora, simplemente guardamos/sobreescribimos
          eventoService.guardar(evento);
          log.info("Evento guardado/actualizado: {}", evento.getNombre());
        }
      }

    } catch (Exception e) {
      log.error("❌ Error al sincronizar con la API externa: {}", e.getMessage());
      // No lanzamos la excepción para no detener el scheduler, solo logueamos
    }
  }
}
