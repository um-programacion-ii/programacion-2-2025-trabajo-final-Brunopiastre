package ar.edu.um.ticketflow.backend.domain.port.in;

import ar.edu.um.ticketflow.backend.domain.Venta;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de entrada para operaciones sobre ventas.
 */
public interface VentaUseCase {

    List<Venta> listarTodas();

    Optional<Venta> buscarPorId(Long id);

    Venta guardar(Venta venta);

    void eliminarPorId(Long id);
}
