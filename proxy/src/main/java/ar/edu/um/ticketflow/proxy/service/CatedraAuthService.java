package ar.edu.um.ticketflow.proxy.service;

import ar.edu.um.ticketflow.proxy.config.ProxyProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CatedraAuthService {
    private final WebClient webClient;
    private final ProxyProperties props;
    private final AtomicReference<String> token = new AtomicReference<>(null);
    private volatile Instant tokenTs = Instant.EPOCH;

    public CatedraAuthService(WebClient webClient, ProxyProperties props) {
        this.webClient = webClient;
        this.props = props;
    }

    public Mono<String> getBearerToken() {
        // Simple caching for 4 minutes
        if (token.get() != null && Instant.now().isBefore(tokenTs.plusSeconds(240))) {
            return Mono.just(token.get());
        }
        return authenticate().map(t -> {
            token.set(t);
            tokenTs = Instant.now();
            return t;
        });
    }

    private Mono<String> authenticate() {
        String url = props.getBaseUrl() + "/api/authenticate"; // verify exact path
        return webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("username", props.getUsername(), "password", props.getPassword()))
                .retrieve()
                .bodyToMono(Map.class)
                .map(body -> {
                    Object tokenValue = body.get("token");
                    if (tokenValue == null) tokenValue = body.get("access_token");
                    if (tokenValue == null) throw new RuntimeException("No token in authenticate response");
                    return tokenValue.toString();
                });
    }
}
