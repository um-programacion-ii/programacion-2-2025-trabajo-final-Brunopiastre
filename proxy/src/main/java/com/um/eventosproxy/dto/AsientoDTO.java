package com.um.eventosproxy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class AsientoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("fila")
    private String fila;

    @JsonProperty("numero")
    private Integer numero;

    @JsonProperty("estado")
    private EstadoAsiento estado;

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public EstadoAsiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsiento estado) {
        this.estado = estado;
    }

    public enum EstadoAsiento {
        LIBRE,
        OCUPADO,
        BLOQUEADO
    }
}