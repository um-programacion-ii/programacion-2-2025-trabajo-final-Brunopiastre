package ar.edu.um.ticketflow.backend.event.application.service;

import ar.edu.um.ticketflow.backend.domain.Evento;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.out.jpa.entity.EventEntity;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.out.jpa.repository.EventoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventoService {

  private final EventoRepository eventoRepository;

  public EventoService(EventoRepository eventoRepository) {
    this.eventoRepository = eventoRepository;
  }

  // LISTAR: Convierte de Entity (BD) a Domain (Java)
  @Transactional(readOnly = true)
  public List<Evento> listarTodo() {
    return eventoRepository.findAll().stream()
      .map(this::mapToDomain)
      .collect(Collectors.toList());
  }

  // CREAR: Convierte de Domain (Java) a Entity (BD)
  @Transactional
  public Evento crear(Evento evento) {
    EventEntity entity = mapToEntity(evento);
    EventEntity guardado = eventoRepository.save(entity);
    return mapToDomain(guardado);
  }

  // BUSCAR: Convierte el Optional
  @Transactional(readOnly = true)
  public Optional<Evento> buscarPorId(Long id) {
    return eventoRepository.findById(id)
      .map(this::mapToDomain);
  }

  @Transactional
  public Evento actualizar(Long id, Evento evento) {
    return eventoRepository.findById(id)
      .map(entity -> {
        entity.setName(evento.getNombre());
        entity.setDescription(evento.getDescripcion());
        entity.setPrice(evento.getPrecio());
        entity.setStartDate(evento.getFecha());
        // Agrega más campos si tu Evento los tiene
        return eventoRepository.save(entity);
      })
      .map(this::mapToDomain)
      .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
  }

  @Transactional
  public void eliminar(Long id) {
    eventoRepository.deleteById(id);
  }

  // --- MÉTODOS DE CONVERSIÓN (MAPPERS) ---

  private Evento mapToDomain(EventEntity entity) {
    Evento evento = new Evento();
    evento.setId(entity.getId());
    evento.setNombre(entity.getName());
    evento.setDescripcion(entity.getDescription());
    evento.setPrecio(entity.getPrice());
    evento.setFecha(entity.getStartDate());
    // evento.setUbicacion(entity.getLocation()); // Si Evento tiene ubicación
    return evento;
  }

  private EventEntity mapToEntity(Evento evento) {
    return EventEntity.builder()
      .id(evento.getId())
      .name(evento.getNombre())
      .description(evento.getDescripcion())
      .price(evento.getPrecio())
      .startDate(evento.getFecha())
      // .location(evento.getUbicacion())
      .build();
  }
}
