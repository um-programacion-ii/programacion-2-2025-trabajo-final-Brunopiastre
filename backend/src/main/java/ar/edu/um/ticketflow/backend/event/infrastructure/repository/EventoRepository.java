package ar.edu.um.ticketflow.backend.event.infrastructure.repository;

import ar.edu.um.ticketflow.backend.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    Optional<Evento> findByIdCatedra(String idCatedra);
}
