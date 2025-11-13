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
                .baseUrl(props.getBaseUrl())
                .build();
    }

    public String obtenerToken(String user, String pass) {
        LoginRequest req = new LoginRequest(user, pass, false);
        LoginResponse resp = webClient.post()
                .uri(props.getEndpoints().getLogin())
                .bodyValue(req)
                .retrieve()
                .bodyToMono(LoginResponse.class)
                .block();

        return resp.id_token();
    }
}
