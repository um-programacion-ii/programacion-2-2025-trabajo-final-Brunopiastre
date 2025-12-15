package ar.edu.um.ticketflow.backend.event.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatedraEventoResumenDto {
    private String titulo;
    private String resumen;
    private String descripcion;
    private OffsetDateTime fecha;
    private Long id;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getResumen() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public OffsetDateTime getFecha() { return fecha; }
    public void setFecha(OffsetDateTime fecha) { this.fecha = fecha; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
