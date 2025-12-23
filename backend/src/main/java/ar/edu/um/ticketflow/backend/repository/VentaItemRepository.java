package ar.edu.um.ticketflow.backend.repository;

import ar.edu.um.ticketflow.backend.domain.VentaItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaItemRepository extends JpaRepository<VentaItem, Long> {
}
