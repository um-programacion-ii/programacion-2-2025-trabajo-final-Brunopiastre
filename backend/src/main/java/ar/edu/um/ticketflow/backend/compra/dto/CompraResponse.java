package ar.edu.um.ticketflow.backend.compra.dto;

import java.util.List;

public class CompraResponse {

    private boolean resultado;
    private String descripcion;
    private Long ventaId;
    private List<AsientoDto> asientos;

    public CompraResponse() {
    }

    public CompraResponse(boolean resultado, String descripcion, Long ventaId, List<AsientoDto> asientos) {
        this.resultado = resultado;
        this.descripcion = descripcion;
        this.ventaId = ventaId;
        this.asientos = asientos;
    }

    public boolean isResultado() { return resultado; }
    public void setResultado(boolean resultado) { this.resultado = resultado; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Long getVentaId() { return ventaId; }
    public void setVentaId(Long ventaId) { this.ventaId = ventaId; }

    public List<AsientoDto> getAsientos() { return asientos; }
    public void setAsientos(List<AsientoDto> asientos) { this.asientos = asientos; }
}
