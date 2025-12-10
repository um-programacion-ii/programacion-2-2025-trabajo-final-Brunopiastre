package ar.edu.um.ticketflow.backend.service;

import ar.edu.um.ticketflow.backend.config.BackendProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class BackendProxyClient {
    private final WebClient webClient;
    private final BackendProperties props;

    public BackendProxyClient(WebClient webClient, BackendProperties props) {
        this.webClient = webClient;
        this.props = props;
    }

    public Mono<Map> getEstadoEvento(String eventoId) {
        String url = props.getBaseUrl() + "/proxy/eventos/" + eventoId + "/estado";
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class);
    }

    public Mono<Map> bloquearAsiento(String eventoId, Map<String, Object> body) {
        String url = props.getBaseUrl() + "/proxy/eventos/" + eventoId + "/bloquear-asiento";
        if (!body.containsKey("ttlSeconds")) body.put("ttlSeconds", 300);
        return webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class);
    }

    public Mono<Void> forzarActualizacion() {
        String url = props.getBaseUrl() + "/proxy/forzar-actualizacion";
        return webClient.post()
                .uri(url)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
