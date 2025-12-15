package ar.edu.um.ticketflow.backend.asientos;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BloqueoAsientosService {

    // key: eventoId:fila:columna
    private final Map<String, AsientoBloqueado> bloqueos = new ConcurrentHashMap<>();

    public void bloquearAsientos(long eventoId, List<AsientoBloqueado> asientos) {
        Instant expiracion = Instant.now().plus(Duration.ofMinutes(5));

        for (AsientoBloqueado asiento : asientos) {
            String key = key(eventoId, asiento.getFila(), asiento.getColumna());
            asiento.setEventoId(eventoId);
            asiento.setExpiracion(expiracion);
            bloqueos.put(key, asiento);
        }
    }

    public boolean estaBloqueado(long eventoId, int fila, int columna) {
        String key = key(eventoId, fila, columna);
        AsientoBloqueado asiento = bloqueos.get(key);
        if (asiento == null) {
            return false;
        }
        if (asiento.estaVencido()) {
            bloqueos.remove(key);
            return false;
        }
        return true;
    }

    private String key(long eventoId, int fila, int columna) {
        return eventoId + ":" + fila + ":" + columna;
    }
}
