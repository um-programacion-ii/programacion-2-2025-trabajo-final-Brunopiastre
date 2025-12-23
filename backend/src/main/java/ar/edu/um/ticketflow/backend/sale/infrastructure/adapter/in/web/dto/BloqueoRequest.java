package ar.edu.um.ticketflow.backend.sale.infrastructure.adapter.in.web.dto;

import java.util.List;
import lombok.Data;

@Data
public class BloqueoRequest {
  private Long eventoId; // [cite: 304]
  private List<AsientoPosicion> asientos; // [cite: 304]

  @Data
  public static class AsientoPosicion {
    private int fila; // [cite: 304]
    private int columna; // [cite: 304]
  }
}
