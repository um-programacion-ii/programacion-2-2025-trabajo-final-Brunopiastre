package ar.edu.um.ticketflow.backend.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EventCacheService {
    private final Map<String, String> eventEstadoRaw = new ConcurrentHashMap<>();

    public void updateEstadoRaw(String eventoId, String rawJson) {
        if (rawJson != null) {
            eventEstadoRaw.put(eventoId, rawJson);
        } else {
            eventEstadoRaw.remove(eventoId);
        }
    }

    public String getEstadoRaw(String eventoId) {
        return eventEstadoRaw.get(eventoId);
    }
}
