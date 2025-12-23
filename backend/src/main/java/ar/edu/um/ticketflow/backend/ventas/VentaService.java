package ar.edu.um.ticketflow.backend.ventas;

import ar.edu.um.ticketflow.backend.compra.dto.CompraRequest;
import ar.edu.um.ticketflow.backend.compra.dto.CompraResponse;
import org.springframework.stereotype.Service;

// CORRECCIÓN: Le damos un nombre de bean explícito para evitar el conflicto.
@Service("ventaRetryService")
public class VentaService {

  private static final int DEFAULT_MAX_REINTENTOS = 3;

  public CompraResponse realizarVentaConReintentos(CompraRequest request) {
    return realizarVentaConReintentos(request, DEFAULT_MAX_REINTENTOS);
  }

  public CompraResponse realizarVentaConReintentos(CompraRequest request, int maxReintentos) {
    int intento = 0;
    Exception ultimaExcepcion = null;

    while (intento < maxReintentos) {
      try {
        intento++;
        // Acá se integrará la llamada real a la cátedra (payload 7)
        return new CompraResponse(
          true,
          "Venta aceptada en intento " + intento,
          null,
          request.getAsientos()
        );
      } catch (Exception e) {
        ultimaExcepcion = e;
      }
    }

    String descripcion = "Venta rechazada luego de " + maxReintentos + " reintentos";
    if (ultimaExcepcion != null) {
      descripcion += ": " + ultimaExcepcion.getMessage();
    }

    return new CompraResponse(false, descripcion, null, request.getAsientos());
  }
}
