package ar.edu.um.ticketflow.backend.application.usecase;

import ar.edu.um.ticketflow.backend.domain.Evento;
import ar.edu.um.ticketflow.backend.domain.port.in.EventoUseCase;
import ar.edu.um.ticketflow.backend.domain.port.out.EventoRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de los casos de uso relacionados con eventos.
 * Orquesta el acceso al repositorio a través del puerto de salida.
 */
@Service
@Transactional
public class EventoUseCaseService implements EventoUseCase {

    private final EventoRepositoryPort eventoRepositoryPort;

    public EventoUseCaseService(EventoRepositoryPort eventoRepositoryPort) {
        this.eventoRepositoryPort = eventoRepositoryPort;
    }

    @Override
    public List<Evento> listarTodos() {
        return eventoRepositoryPort.listarTodos();
    }

    @Override
    public Optional<Evento> buscarPorId(Long id) {
        return eventoRepositoryPort.buscarPorId(id);
    }

    @Override
    public Optional<Evento> buscarPorIdCatedra(String idCatedra) {
        return eventoRepositoryPort.buscarPorIdCatedra(idCatedra);
    }

    @Override
    public Evento guardar(Evento evento) {
        return eventoRepositoryPort.guardar(evento);
    }
}
