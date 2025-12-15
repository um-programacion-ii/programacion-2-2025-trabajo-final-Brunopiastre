package ar.edu.um.ticketflow.proxy.service;

import ar.edu.um.ticketflow.proxy.config.ProxyProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class CatedraClientService {
    private final WebClient webClient;
    private final ProxyProperties props;
    private final CatedraAuthService authService;

    public CatedraClientService(WebClient webClient, ProxyProperties props, CatedraAuthService authService) {
        this.webClient = webClient;
        this.props = props;
        this.authService = authService;
    }

    public Mono<Map> bloquearAsiento(String eventoId, Map<String, Object> body) {
        // Ensure TTL = 300 default
        body.putIfAbsent("ttlSeconds", 300);
        String url = props.getBaseUrl() + "/api/endpoints/v1/bloquear-asiento";
        return authService.getBearerToken().flatMap(token ->
                webClient.post().uri(url)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(body)
                        .retrieve()
                        .bodyToMono(Map.class)
        );
    }

    public Mono<Void> forzarActualizacion() {
        String url = props.getBaseUrl() + "/api/endpoints/v1/forzar-actualizacion";
        return authService.getBearerToken().flatMap(token ->
                webClient.post().uri(url)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .retrieve()
                        .bodyToMono(Void.class)
        );
    }
}
