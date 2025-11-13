package ar.edu.um.ticketflow.backend.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class EventSyncScheduler {

    // Por ahora usamos un cache vacío hasta que se implemente el Issue #7
    private List<Object> cache = Collections.emptyList();

    // Cada 5 minutos (300000 ms)
    @Scheduled(fixedRate = 300000)
    public void sync() {
        try {
            // TEMPORAL: más adelante se reemplaza por CatedraClientService
            System.out.println("[SYNC] Ejecutando sincronización de eventos...");
            cache = Collections.emptyList(); // Placeholder
            System.out.println("[SYNC] Sincronización finalizada");
        } catch (Exception e) {
            System.out.println("[SYNC] Error sincronizando eventos: " + e.getMessage());
        }
    }

    public List<Object> obtenerCache() {
        return cache;
    }
}
