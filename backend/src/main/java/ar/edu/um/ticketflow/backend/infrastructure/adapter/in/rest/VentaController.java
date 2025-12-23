package ar.edu.um.ticketflow.backend.infrastructure.adapter.in.rest;

import ar.edu.um.ticketflow.backend.domain.Venta;
import ar.edu.um.ticketflow.backend.domain.port.in.VentaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST hexagonal para ventas.
 */
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaUseCase ventaUseCase;

    public VentaController(VentaUseCase ventaUseCase) {
        this.ventaUseCase = ventaUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listar() {
        return ResponseEntity.ok(ventaUseCase.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarPorId(@PathVariable Long id) {
        return ventaUseCase.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venta> crear(@RequestBody Venta venta) {
        Venta creada = ventaUseCase.guardar(venta);
        return ResponseEntity.ok(creada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ventaUseCase.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
