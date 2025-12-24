package ar.edu.um.ticketflow.backend.event.infrastructure.adapter.in.scheduler;

import ar.edu.um.ticketflow.backend.event.application.service.EventoService;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.out.web.dto.CatedraEventDto;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.out.client.CatedraClient;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.out.jpa.entity.EventEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventSyncScheduler {

  private final EventoService eventoService;
  private final CatedraClient catedraClient;

  public EventSyncScheduler(EventoService eventoService, CatedraClient catedraClient) {
    this.eventoService = eventoService;
    this.catedraClient = catedraClient;
  }

  @Scheduled(fixedRate = 3600000) // Cada 1 hora
  public void syncEvents() {
    System.out.println("--- Iniciando Sincronización de Eventos ---");

    // 1. Delegamos la obtención de datos al cliente
    List<CatedraEventDto> eventosExternos = catedraClient.fetchEvents();

    if (eventosExternos.isEmpty()) {
      System.out.println("No se encontraron eventos o falló la conexión.");
      return;
    }

    // 2. Procesamos con upsert en el servicio (no duplicar)
    for (CatedraEventDto dto : eventosExternos) {
      try {
        eventoService.actualizarOcrearEvento(dto);
      } catch (Exception e) {
        System.err.println("Error sincronizando evento id=" + dto.getId() + ": " + e.getMessage());
      }
    }
  }

  // Forzar una sincronización inicial al levantar la app
  @org.springframework.context.event.EventListener(org.springframework.boot.context.event.ApplicationReadyEvent.class)
  public void initialSyncOnStartup() {
    try {
      syncEvents();
    } catch (Exception e) {
      System.err.println("Sincronización inicial fallida: " + e.getMessage());
    }
  }
}
