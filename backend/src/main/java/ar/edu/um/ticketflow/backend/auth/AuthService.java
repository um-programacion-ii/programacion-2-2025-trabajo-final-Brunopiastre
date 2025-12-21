package ar.edu.um.ticketflow.backend.auth;

import ar.edu.um.ticketflow.backend.config.CatedraProperties;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final CatedraProperties props;

  public AuthService(CatedraProperties props) {
    this.props = props;
  }

  public String getLoginUrl() {
    // Usamos props.getUrl() directamente
    return props.getUrl() + "/login";
  }

  public String getToken() {
    // Usamos props.getToken() directamente
    return props.getToken();
  }

  // Si tenías otros métodos, asegúrate de usar props.getUrl() en vez de props.getApi().getUrl()
}
