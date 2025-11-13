package ar.edu.um.ticketflow.backend.scheduler;

import ar.edu.um.ticketflow.backend.client.CatedraClientService;
import ar.edu.um.ticketflow.backend.client.dto.EventoResumidoDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class EventSyncScheduler {

    private final CatedraClientService client;
    private List<EventoResumidoDto> cache = Collections.emptyList();

    public EventSyncScheduler(CatedraClientService client) {
        this.client = client;
    }

    @Scheduled(fixedRate = 300000) // cada 5 minutos
    public void sync() {
        try {
            cache = client.obtenerEventosResumidos();
            System.out.println("Sincronización OK: " + cache.size() + " eventos");
        } catch (Exception e) {
            System.out.println("Error sincronizando eventos: " + e.getMessage());
        }
    }

    public List<EventoResumidoDto> obtenerCache() {
        return cache;
    }
}
