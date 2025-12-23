package ar.edu.um.ticketflow.backend.repository;

import ar.edu.um.ticketflow.backend.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
