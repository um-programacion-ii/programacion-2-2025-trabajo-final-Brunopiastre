package ar.edu.um.ticketflow.backend.auth;

import ar.edu.um.ticketflow.backend.config.CatedraProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthService {

  private final WebClient webClient;
  private final CatedraProperties props;

  public AuthService(CatedraProperties props) {
    this.props = props;
    this.webClient = WebClient.builder()
      // CORRECCIÓN 1: De getBaseUrl() a getApi().getUrl()
      .baseUrl(props.getApi().getUrl())
      .build();
  }

  public String obtenerToken(String user, String pass) {
    LoginRequest req = new LoginRequest(user, pass, false);
    LoginResponse resp = webClient.post()
      // CORRECCIÓN 2: getLogin() ya es correcto si CatedraProperties.Endpoints fue actualizado
      .uri(props.getEndpoints().getLogin())
      .bodyValue(req)
      .retrieve()
      .bodyToMono(LoginResponse.class)
      .block();

    return resp.id_token();
  }
}
