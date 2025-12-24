package ar.edu.um.ticketflow.backend.event.infrastructure.adapter.in.scheduler;

import ar.edu.um.ticketflow.backend.event.application.service.EventoService;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.in.web.dto.CatedraEventDto;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.out.client.CatedraClient;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.out.jpa.entity.EventEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
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

    // 2. Procesamos
    for (CatedraEventDto dto : eventosExternos) {
      EventEntity evento = new EventEntity();

      // Mapeo usando los datos del PDF
      evento.setId(dto.getId());
      evento.setNombre(dto.getTitulo());       // PDF: "titulo" -> Entidad: nombre
      evento.setDescripcion(dto.getDescripcion());

      // Parseo de fecha (String -> ZonedDateTime)
      // Asegúrate de manejar el formato ISO 8601 que manda la cátedra
      try {
        evento.setFecha(ZonedDateTime.parse(dto.getFecha()));
      } catch (Exception e) {
        System.err.println("Error parseando fecha para evento " + dto.getId());
        continue; // Saltamos este evento si la fecha está mal
      }

      evento.setBasePrice(dto.getPrecioEntrada()); // PDF: "precioEntrada"

      // Requisito 4.1 y Payload 4: Usar distribución real
      if (dto.getFilaAsientos() != null && dto.getColumnAsientos() != null) {
        evento.setRowsCount(dto.getFilaAsientos());
        evento.setColumnsCount(dto.getColumnAsientos());
        evento.setCapacity(dto.getFilaAsientos() * dto.getColumnAsientos());
      } else {
        // Fallback por si la cátedra manda null (defensivo)
        evento.setRowsCount(10);
        evento.setColumnsCount(10);
        evento.setCapacity(100);
      }

      // IMPORTANTE: Debes tener un método syncEvent o createOrUpdate en tu servicio
      // para no duplicar eventos cada vez que corre el scheduler.
      eventoService.createEvent(evento);

      System.out.println("Sincronizado: " + dto.getTitulo());
    }
  }
}
