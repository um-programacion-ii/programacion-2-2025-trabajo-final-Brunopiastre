package ar.edu.um.ticketflow.backend.service;

import ar.edu.um.ticketflow.backend.domain.Evento;
import ar.edu.um.ticketflow.backend.domain.Venta;
import ar.edu.um.ticketflow.backend.domain.VentaEstado;
import ar.edu.um.ticketflow.backend.domain.VentaItem;
import ar.edu.um.ticketflow.backend.repository.VentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Transactional
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public Venta crearVentaPendiente(Evento evento, BigDecimal monto) {
        Venta venta = new Venta(evento, LocalDateTime.now(), VentaEstado.PENDIENTE);
        venta.setMontoTotal(monto);
        return ventaRepository.save(venta);
    }

    public Venta agregarItem(Venta venta, Integer fila, Integer columna, String nombrePasajero) {
        VentaItem item = new VentaItem(fila, columna, nombrePasajero);
        venta.agregarItem(item);
        return ventaRepository.save(venta);
    }

    public Venta marcarVentaConfirmada(Venta venta) {
        venta.setEstado(VentaEstado.CONFIRMADA);
        return ventaRepository.save(venta);
    }

    public Venta marcarVentaFallida(Venta venta) {
        venta.setEstado(VentaEstado.FALLIDA);
        return ventaRepository.save(venta);
    }

    public Venta guardar(Venta venta) {
        return ventaRepository.save(venta);
    }
}
