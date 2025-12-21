package ar.edu.um.ticketflow.backend.domain;

// IMPORT CORREGIDO:
import ar.edu.um.ticketflow.backend.event.infrastructure.adapter.out.jpa.entity.EventEntity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "evento_id", nullable = false)
  private EventEntity evento;

  @Column(name = "fecha_hora", nullable = false)
  private LocalDateTime fechaHora;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private VentaEstado estado;

  @Column(name = "monto_total", precision = 10, scale = 2)
  private BigDecimal montoTotal;

  @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<VentaItem> items = new ArrayList<>();

  public Venta() { }

  public Venta(EventEntity evento, LocalDateTime fechaHora, VentaEstado estado) {
    this.evento = evento;
    this.fechaHora = fechaHora;
    this.estado = estado;
  }

  public void agregarItem(VentaItem item) {
    item.setVenta(this);
    this.items.add(item);
  }

  public Long getId() { return id; }

  public EventEntity getEvento() { return evento; }

  public void setEvento(EventEntity evento) { this.evento = evento; }

  public LocalDateTime getFechaHora() { return fechaHora; }

  public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

  public VentaEstado getEstado() { return estado; }

  public void setEstado(VentaEstado estado) { this.estado = estado; }

  public BigDecimal getMontoTotal() { return montoTotal; }

  public void setMontoTotal(BigDecimal montoTotal) { this.montoTotal = montoTotal; }

  public List<VentaItem> getItems() { return items; }

  public void setItems(List<VentaItem> items) { this.items = items; }
}
