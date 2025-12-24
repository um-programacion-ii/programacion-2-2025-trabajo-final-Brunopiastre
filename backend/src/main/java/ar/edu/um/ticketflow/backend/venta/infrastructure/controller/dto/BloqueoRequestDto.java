package ar.edu.um.ticketflow.backend.venta.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloqueoRequestDto {
  private Long eventoId;
  private List<AsientoBloqueoDto> asientos;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class AsientoBloqueoDto {
    private int fila;
    private int columna;
  }
}
