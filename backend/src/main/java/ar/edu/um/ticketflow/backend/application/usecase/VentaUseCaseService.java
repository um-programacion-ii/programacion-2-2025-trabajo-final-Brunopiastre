package ar.edu.um.ticketflow.backend.application.usecase;

import ar.edu.um.ticketflow.backend.domain.Venta;
import ar.edu.um.ticketflow.backend.domain.port.in.VentaUseCase;
import ar.edu.um.ticketflow.backend.domain.port.out.VentaRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de los casos de uso relacionados con ventas.
 */
@Service
@Transactional
public class VentaUseCaseService implements VentaUseCase {

    private final VentaRepositoryPort ventaRepositoryPort;

    public VentaUseCaseService(VentaRepositoryPort ventaRepositoryPort) {
        this.ventaRepositoryPort = ventaRepositoryPort;
    }

    @Override
    public List<Venta> listarTodas() {
        return ventaRepositoryPort.listarTodas();
    }

    @Override
    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepositoryPort.buscarPorId(id);
    }

    @Override
    public Venta guardar(Venta venta) {
        return ventaRepositoryPort.guardar(venta);
    }

    @Override
    public void eliminarPorId(Long id) {
        ventaRepositoryPort.eliminarPorId(id);
    }
}
