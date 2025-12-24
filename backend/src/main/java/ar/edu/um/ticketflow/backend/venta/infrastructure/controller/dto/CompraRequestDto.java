package ar.edu.um.ticketflow.backend.venta.infrastructure.controller.dto;

import java.util.List;

public class CompraRequestDto {
  private Long eventoId;
  private Long usuarioId;
  private List<AsientoDto> asientos;

  // Getters y Setters
  public Long getEventoId() { return eventoId; }
  public void setEventoId(Long eventoId) { this.eventoId = eventoId; }

  public Long getUsuarioId() { return usuarioId; }
  public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

  public List<AsientoDto> getAsientos() { return asientos; }
  public void setAsientos(List<AsientoDto> asientos) { this.asientos = asientos; }

  public static class AsientoDto {
    private int fila;
    private int columna;

    public int getFila() { return fila; }
    public void setFila(int fila) { this.fila = fila; }

    public int getColumna() { return columna; }
    public void setColumna(int columna) { this.columna = columna; }
  }
}
