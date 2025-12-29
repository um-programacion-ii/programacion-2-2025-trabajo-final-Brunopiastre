package com.um.eventosproxy.dto;

import java.io.Serializable;
import java.util.List;

public class BloqueoAsientosRequestDTO implements Serializable {

    private Long eventoId;
    private List<AsientoBloqueoDTO> asientos;

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public List<AsientoBloqueoDTO> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<AsientoBloqueoDTO> asientos) {
        this.asientos = asientos;
    }

    public static class AsientoBloqueoDTO implements Serializable {
        private Integer fila;
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
}