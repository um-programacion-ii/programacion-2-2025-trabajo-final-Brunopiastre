package ar.edu.um.ticketflow.backend.compra.dto;

public class AsientoDto {

    private int fila;
    private int columna;
    private String persona;

    public AsientoDto() {
    }

    public AsientoDto(int fila, int columna, String persona) {
        this.fila = fila;
        this.columna = columna;
        this.persona = persona;
    }

    public int getFila() { return fila; }
    public void setFila(int fila) { this.fila = fila; }

    public int getColumna() { return columna; }
    public void setColumna(int columna) { this.columna = columna; }

    public String getPersona() { return persona; }
    public void setPersona(String persona) { this.persona = persona; }
}
