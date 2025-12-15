package ar.edu.um.ticketflow.backend.event.application.service;

import ar.edu.um.ticketflow.backend.domain.Evento;
import ar.edu.um.ticketflow.backend.event.infrastructure.repository.EventoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento guardarOActualizarEvento(String idCatedra,
                                           String nombre,
                                           LocalDateTime fechaHora,
                                           String lugar,
                                           String descripcion) {

        Optional<Evento> existente = eventoRepository.findByIdCatedra(idCatedra);

        if (existente.isPresent()) {
            Evento e = existente.get();
            e.setNombre(nombre);
            e.setFechaHora(fechaHora);
            e.setLugar(lugar);
            e.setDescripcion(descripcion);
            e.setUltimaActualizacion(LocalDateTime.now());
            return eventoRepository.save(e);
        }

        Evento nuevo = new Evento(idCatedra, nombre, fechaHora, lugar, descripcion);
        return eventoRepository.save(nuevo);
    }

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> buscarPorId(Long id) {
        return eventoRepository.findById(id);
    }

    public Optional<Evento> buscarPorIdCatedra(String idCatedra) {
        return eventoRepository.findByIdCatedra(idCatedra);
    }
}
