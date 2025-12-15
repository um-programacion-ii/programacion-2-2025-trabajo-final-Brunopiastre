package ar.edu.um.ticketflow.backend.client.dto;

import java.time.LocalDateTime;

// Nota: Asumo que usas Lombok (@Getter, @Setter) para simplificar
// Si no usas Lombok, debes añadir getters, setters y un constructor.

public class EventoResumidoDto {

    private Long id;
    private String nombre;
    private LocalDateTime fechaInicio;
    private String lugar;
    private String categoria;
    
    // Si usas Lombok, añade las anotaciones aquí:
    // @Getter
    // @Setter
    // ...
    
}
