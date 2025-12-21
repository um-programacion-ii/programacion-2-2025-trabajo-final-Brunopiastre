package ar.edu.um.ticketflow.backend.event.infrastructure.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data                // Crea getters y setters automáticos
@Builder             // Permite .builder()
@NoArgsConstructor   // Constructor vacío
@AllArgsConstructor  // Constructor completo
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatedraEventoResumenDto {

  private Long id;

  // Si la Cátedra manda "titulo", lo guardamos aquí como "nombre"
  @JsonAlias("titulo")
  private String nombre;

  @JsonAlias("resumen")
  private String descripcion;

  // Ajustamos la fecha para que sea compatible con LocalDateTime
  // Si la cátedra manda OffsetDateTime, Jackson suele convertirlo bien.
  private LocalDateTime fecha;

  // Agregamos precio (necesario para el Scheduler)
  private BigDecimal precio;

  // Agregamos capacidad (por si acaso)
  private Integer capacidad;
}
