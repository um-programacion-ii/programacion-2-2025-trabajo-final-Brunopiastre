package ar.edu.um.ticketflow.backend.event.application.service;

import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.out.jpa.entity.EventEntity;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.out.jpa.repository.EventoRepository;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.out.web.dto.SeatDto;
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.in.web.dto.CatedraEventDto; // IMPORTANTE
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

  private final EventoRepository eventoRepository;

  public EventoService(EventoRepository eventoRepository) {
    this.eventoRepository = eventoRepository;
  }

  public List<EventEntity> getAllEvents() {
    return eventoRepository.findAll();
  }

  public EventEntity createEvent(EventEntity event) {
    return eventoRepository.save(event);
  }

  public Optional<EventEntity> getEventById(Long id) {
    return eventoRepository.findById(id);
  }

  public List<SeatDto> getSeatsForEvent(Long eventId) {
    EventEntity event = eventoRepository.findById(eventId)
      .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + eventId));

    List<SeatDto> seats = new ArrayList<>();
    for (int fila = 1; fila <= event.getRowsCount(); fila++) {
      double precioFila = event.getBasePrice() * Math.pow(1.05, fila - 1);
      for (int columna = 1; columna <= event.getColumnsCount(); columna++) {
        seats.add(new SeatDto(fila, columna, precioFila, true));
      }
    }
    return seats; // El return va acá
  }

  // ESTE MÉTODO VA AFUERA DEL ANTERIOR
  public void actualizarOcrearEvento(CatedraEventDto dto) {
    EventEntity evento = eventoRepository.findById(dto.getId()).orElse(new EventEntity());

    evento.setId(dto.getId());
    evento.setNombre(dto.getNombre());
    evento.setDescripcion(dto.getDescripcion());
    evento.setFecha(dto.getFecha());
    evento.setBasePrice(dto.getPrecio());
    evento.setCapacity(dto.getCapacidad());

    if (evento.getRowsCount() == 0) {
      evento.setRowsCount(10);
      evento.setColumnsCount(10);
    }

    eventoRepository.save(evento);
    System.out.println("✅ Evento sincronizado: " + evento.getNombre());
  }
}
