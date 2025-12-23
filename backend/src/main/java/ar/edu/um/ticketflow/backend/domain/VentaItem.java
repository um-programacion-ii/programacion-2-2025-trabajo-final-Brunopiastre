package ar.edu.um.ticketflow.backend.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "venta_items")
public class VentaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;

    @Column(nullable = false)
    private Integer fila;

    @Column(nullable = false)
    private Integer columna;

    @Column(name = "nombre_pasajero", length = 200)
    private String nombrePasajero;

    public VentaItem() { }

    public VentaItem(Integer fila, Integer columna, String nombrePasajero) {
        this.fila = fila;
        this.columna = columna;
        this.nombrePasajero = nombrePasajero;
    }

    public Long getId() { return id; }

    public Venta getVenta() { return venta; }

    public void setVenta(Venta venta) { this.venta = venta; }

    public Integer getFila() { return fila; }

    public void setFila(Integer fila) { this.fila = fila; }

    public Integer getColumna() { return columna; }

    public void setColumna(Integer columna) { this.columna = columna; }

    public String getNombrePasajero() { return nombrePasajero; }

    public void setNombrePasajero(String nombrePasajero) { this.nombrePasajero = nombrePasajero; }
}
