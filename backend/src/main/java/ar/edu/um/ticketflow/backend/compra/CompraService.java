package ar.edu.um.ticketflow.backend.compra;

import ar.edu.um.ticketflow.backend.compra.dto.CompraRequest;
import ar.edu.um.ticketflow.backend.compra.dto.CompraResponse;
import ar.edu.um.ticketflow.backend.compra.dto.AsientoDto;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    public CompraResponse realizarCompra(CompraRequest request) {
        // Acá más adelante se integrará:
        // 1) Bloqueo de asientos (payload 6)
        // 2) Venta (payload 7)
        // 3) Manejo de errores y estados

        return new CompraResponse(
                true,
                "Compra registrada (implementación pendiente de integrar con cátedra)",
                null,
                request.getAsientos()
        );
    }
}
