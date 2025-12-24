package ar.edu.um.ticketflow.backend.venta.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
public class Venta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id", nullable = false)
  private Long usuarioId;

  @Column(name = "evento_id", nullable = false)
  private Long eventoId;

  private LocalDateTime fechaVenta;
  private Double total;
  private Integer cantidadAsientos;

  public Venta() {
  }

  // --- GETTERS Y SETTERS MANUALES (Para que no falle nunca) ---

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public Long getUsuarioId() { return usuarioId; }
  public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

  public Long getEventoId() { return eventoId; }
  public void setEventoId(Long eventoId) { this.eventoId = eventoId; }

  public LocalDateTime getFechaVenta() { return fechaVenta; }
  public void setFechaVenta(LocalDateTime fechaVenta) { this.fechaVenta = fechaVenta; }

  public Double getTotal() { return total; }
  public void setTotal(Double total) { this.total = total; }

  public Integer getCantidadAsientos() { return cantidadAsientos; }
  public void setCantidadAsientos(Integer cantidadAsientos) { this.cantidadAsientos = cantidadAsientos; }
}
