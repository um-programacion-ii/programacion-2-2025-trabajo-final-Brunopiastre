package com.um.eventosproxy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BloqueoAsientosResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("resultado")
    private Boolean resultado;

    @JsonProperty("exitoso")
    private Boolean exitoso;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("mensaje")
    private String mensaje;

    @JsonProperty("eventoId")
    private Long eventoId;

    @JsonProperty("asientos")
    private List<AsientoConEstadoDTO> asientos;

    @JsonProperty("asientosBloqueados")
    private List<AsientoBloqueoDTO> asientosBloqueados;

    @JsonProperty("asientosNoDisponibles")
    private List<AsientoBloqueoDTO> asientosNoDisponibles;

    // --- Getters y Setters Manuales ---

    public Boolean getResultado() {
        return resultado;
    }

    public void setResultado(Boolean resultado) {
        this.resultado = resultado;
    }

    public Boolean getExitoso() {
        return exitoso;
    }

    public void setExitoso(Boolean exitoso) {
        this.exitoso = exitoso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public List<AsientoConEstadoDTO> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<AsientoConEstadoDTO> asientos) {
        this.asientos = asientos;
    }

    public List<AsientoBloqueoDTO> getAsientosBloqueados() {
        return asientosBloqueados;
    }

    public void setAsientosBloqueados(List<AsientoBloqueoDTO> asientosBloqueados) {
        this.asientosBloqueados = asientosBloqueados;
    }

    public List<AsientoBloqueoDTO> getAsientosNoDisponibles() {
        return asientosNoDisponibles;
    }

    public void setAsientosNoDisponibles(List<AsientoBloqueoDTO> asientosNoDisponibles) {
        this.asientosNoDisponibles = asientosNoDisponibles;
    }

    // Métodos helper
    public Boolean obtenerExitoso() {
        return resultado != null ? resultado : (exitoso != null ? exitoso : false);
    }

    public String obtenerMensaje() {
        return descripcion != null ? descripcion : (mensaje != null ? mensaje : "");
    }

    // --- Clases Internas ---

    public static class AsientoBloqueoDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        @JsonProperty("fila")
        private Integer fila;

        @JsonProperty("columna")
        private Integer columna;

        public Integer getFila() {
            return fila;
        }

        public void setFila(Integer fila) {
            this.fila = fila;
        }

        public Integer getColumna() {
            return columna;
        }

        public void setColumna(Integer columna) {
            this.columna = columna;
        }
    }

    public static class AsientoConEstadoDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        @JsonProperty("fila")
        private Integer fila;

        @JsonProperty("columna")
        private Integer columna;

        @JsonProperty("estado")
        private String estado;

        public Integer getFila() {
            return fila;
        }

        public void setFila(Integer fila) {
            this.fila = fila;
        }

        public Integer getColumna() {
            return columna;
        }

        public void setColumna(Integer columna) {
            this.columna = columna;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }
    }
}