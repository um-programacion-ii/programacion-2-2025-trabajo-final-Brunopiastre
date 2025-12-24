package ar.edu.um.ticketflow.backend.auth;

import ar.edu.um.ticketflow.backend.config.CatedraProperties;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final CatedraProperties props;

  public AuthService(CatedraProperties props) {
    this.props = props;
  }

  // --- MÉTODOS QUE FALTABAN ---

  // El Scheduler llama a esto para saber a dónde conectarse
  public String getUrl() {
    return props.getUrl();
  }

  // El Scheduler llama a esto para obtener el token del application.properties
  public String getToken() {
    return props.getToken();
  }
}
