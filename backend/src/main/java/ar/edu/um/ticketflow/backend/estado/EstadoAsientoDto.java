package ar.edu.um.ticketflow.backend.estado;

public class EstadoAsientoDto {

    private int fila;
    private int columna;
    private String estado;

    public EstadoAsientoDto() {
    }

    public EstadoAsientoDto(int fila, int columna, String estado) {
        this.fila = fila;
        this.columna = columna;
        this.estado = estado;
    }

    public int getFila() { return fila; }
    public void setFila(int fila) { this.fila = fila; }

    public int getColumna() { return columna; }
    public void setColumna(int columna) { this.columna = columna; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
