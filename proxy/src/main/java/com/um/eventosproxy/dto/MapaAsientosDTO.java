package com.um.eventosproxy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MapaAsientosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("eventoId")
    private Long eventoId;

    @JsonProperty("asientos")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AsientoDTO> asientos;

    @JsonProperty("matriz")
    private List<String> matriz = new ArrayList<>();

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public List<AsientoDTO> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<AsientoDTO> asientos) {
        this.asientos = asientos;
    }

    public List<String> getMatriz() {
        return matriz;
    }

    public void setMatriz(List<String> matriz) {
        this.matriz = matriz;
    }
}