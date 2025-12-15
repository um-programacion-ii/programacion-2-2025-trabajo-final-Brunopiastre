package ar.edu.um.ticketflow.backend.event.application.service;

import ar.edu.um.ticketflow.backend.domain.Evento;
import ar.edu.um.ticketflow.backend.event.infrastructure.client.CatedraClient;
import ar.edu.um.ticketflow.backend.event.infrastructure.repository.EventoRepository;
import ar.edu.um.ticketflow.backend.event.infrastructure.web.dto.CatedraEventoResumenDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SincronizacionService {
    private final EventoRepository eventoRepository;
    private final CatedraClient catedraClient;

    public SincronizacionService(EventoRepository eventoRepository, CatedraClient catedraClient) {
        this.eventoRepository = eventoRepository;
        this.catedraClient = catedraClient;
    }

    /**
     * Sincroniza eventos desde el servicio de la cátedra y devuelve la cantidad de eventos procesados.
     */
    public int sincronizarEventos() {
        List<CatedraEventoResumenDto> externos = catedraClient.listarEventosResumidos();
        for (CatedraEventoResumenDto ext : externos) {
            String idCatedra = String.valueOf(ext.getId());
            Evento ev = eventoRepository.findByIdCatedra(idCatedra)
                    .orElse(new Evento());
            ev.setIdCatedra(idCatedra);
            ev.setNombre(ext.getTitulo());
            ev.setDescripcion(ext.getDescripcion());
            LocalDateTime fecha = ext.getFecha() != null ? ext.getFecha().toLocalDateTime() : LocalDateTime.now();
            ev.setFechaHora(fecha);
            // lugar no viene en payload resumido
            eventoRepository.save(ev);
        }
        return externos.size();
    }
}
