package ar.edu.um.ticketflow.backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "catedra")
@Data
public class CatedraProperties {
  private String url;
  private String token;
}
