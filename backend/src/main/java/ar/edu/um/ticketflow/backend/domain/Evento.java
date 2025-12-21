package ar.edu.um.ticketflow.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
  private Long id;
  private String nombre;
  private String descripcion;
  private Double precio;
  private Integer capacidad;
  private LocalDateTime fecha;
}
