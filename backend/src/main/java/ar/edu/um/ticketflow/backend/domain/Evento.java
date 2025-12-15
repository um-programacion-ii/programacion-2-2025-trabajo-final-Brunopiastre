package ar.edu.um.ticketflow.backend.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_catedra", nullable = false, unique = true, length = 64)
    private String idCatedra;

    @Column(nullable = false, length = 200)
    private String nombre;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(length = 200)
    private String lugar;

    @Column(length = 500)
    private String descripcion;

    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;

    public Evento() { }

    public Evento(String idCatedra, String nombre, LocalDateTime fechaHora, String lugar, String descripcion) {
        this.idCatedra = idCatedra;
        this.nombre = nombre;
        this.fechaHora = fechaHora;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.ultimaActualizacion = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public String getIdCatedra() { return idCatedra; }

    public void setIdCatedra(String idCatedra) { this.idCatedra = idCatedra; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDateTime getFechaHora() { return fechaHora; }

    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getLugar() { return lugar; }

    public void setLugar(String lugar) { this.lugar = lugar; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getUltimaActualizacion() { return ultimaActualizacion; }

    public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) { this.ultimaActualizacion = ultimaActualizacion; }
}
