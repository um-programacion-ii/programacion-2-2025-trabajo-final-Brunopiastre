package ar.edu.um.ticketflow.backend.sale.infrastructure.adapter.in.web.dto;

import java.util.List;
import lombok.Data;

@Data
public class VentaRequest {
  private Long eventoId; //
  private String fecha; // Formato ISO: "2025-08-17T20:00:00.000Z"
  private Double precioVenta; //
  private List<AsientoVenta> asientos; //

  @Data
  public static class AsientoVenta {
    private int fila; //
    private int columna; //
    private String persona; // Nombre y apellido del asistente [cite: 60, 309]
  }
}
