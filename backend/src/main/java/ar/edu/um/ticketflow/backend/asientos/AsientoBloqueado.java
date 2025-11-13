package ar.edu.um.ticketflow.backend.asientos;

import java.time.Instant;

public class AsientoBloqueado {

    private long eventoId;
    private int fila;
    private int columna;
    private Instant expiracion;

    public AsientoBloqueado() {
    }

    public AsientoBloqueado(long eventoId, int fila, int columna, Instant expiracion) {
        this.eventoId = eventoId;
        this.fila = fila;
        this.columna = columna;
        this.expiracion = expiracion;
    }

    public long getEventoId() { return eventoId; }
    public void setEventoId(long eventoId) { this.eventoId = eventoId; }

    public int getFila() { return fila; }
    public void setFila(int fila) { this.fila = fila; }

    public int getColumna() { return columna; }
    public void setColumna(int columna) { this.columna = columna; }

    public Instant getExpiracion() { return expiracion; }
    public void setExpiracion(Instant expiracion) { this.expiracion = expiracion; }

    public boolean estaVencido() {
        return Instant.now().isAfter(expiracion);
    }
}
