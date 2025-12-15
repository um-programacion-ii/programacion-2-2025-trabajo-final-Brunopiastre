package ar.edu.um.ticketflow.backend.compra.dto;

import java.util.List;

public class CompraRequest {

    private long eventoId;
    private double precioVenta;
    private List<AsientoDto> asientos;

    public CompraRequest() {
    }

    public long getEventoId() { return eventoId; }
    public void setEventoId(long eventoId) { this.eventoId = eventoId; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public List<AsientoDto> getAsientos() { return asientos; }
    public void setAsientos(List<AsientoDto> asientos) { this.asientos = asientos; }
}
