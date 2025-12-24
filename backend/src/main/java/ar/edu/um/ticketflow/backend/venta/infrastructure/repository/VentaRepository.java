package ar.edu.um.ticketflow.backend.venta.infrastructure.repository;

import ar.edu.um.ticketflow.backend.venta.domain.entity.Venta; // <--- OJO AQUÍ
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}
