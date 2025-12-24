package ar.edu.um.ticketflow.backend.venta.infrastructure.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class BloqueoResponseDto {
  private boolean resultado;     // true = éxito, false = falló
  private String descripcion;    // "Asientos bloqueados con exito" o error
  private Long eventoId;
  private List<AsientoEstadoDto> asientos;

  @Data
  public static class AsientoEstadoDto {
    private int fila;
    private int columna;
    private String estado;     // "Bloqueo exitoso", "Ocupado", etc.
  }
}
