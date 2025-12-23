package ar.edu.um.ticketflow.backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

// OJO: NO agregues @Component ni @Configuration aquí.
// Spring ya la carga porque la invocamos desde el Main.

@Data
@ConfigurationProperties(prefix = "catedra")
public class CatedraProperties {
  private String url;
  private String token;
}
