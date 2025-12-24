package ar.edu.um.ticketflow.backend.sale.application.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WizardState implements Serializable {
  // Importante para que Redis pueda leer y escribir el objeto sin errores
  private static final long serialVersionUID = 1L;

  private Long eventoId;
  private String pasoActual; // Ej: "SELECCION", "DATOS_PERSONALES", "CONFIRMACION"
  private List<AsientoSeleccionado> asientos;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class AsientoSeleccionado implements Serializable {
    private int fila;
    private int columna;
    private String nombrePersona; // Se llenará en el segundo paso del Wizard
  }
}
