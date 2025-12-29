package com.um.eventosproxy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class EventoChangeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("eventoId")
    private Long eventoId;

    @JsonProperty("tipoCambio")
    private TipoCambio tipoCambio;

    @JsonProperty("evento")
    private Object evento; // Puede ser null si es DELETE

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public TipoCambio getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(TipoCambio tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public Object getEvento() {
        return evento;
    }

    public void setEvento(Object evento) {
        this.evento = evento;
    }

    public enum TipoCambio {
        CREATE,
        UPDATE,
        DELETE,
        CANCEL
    }
}