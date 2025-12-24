package ar.edu.um.ticketflow.backend.event.infrastructure.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data; // <--- VITAL

@Data // <--- ESTO CREA LOS MÉTODOS getId(), getNombre()...
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatedraEventDto {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("nombre")
  private String nombre;

  @JsonProperty("descripcion")
  private String descripcion;

  @JsonProperty("fecha")
  private String fecha;

  @JsonProperty("precio")
  private Double precio;

  @JsonProperty("capacidad")
  private Integer capacidad;
}
