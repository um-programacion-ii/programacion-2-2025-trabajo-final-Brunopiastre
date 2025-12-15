package ar.edu.um.ticketflow.backend.event.infrastructure.client;

import ar.edu.um.ticketflow.backend.config.CatedraProperties;
import ar.edu.um.ticketflow.backend.event.infrastructure.web.dto.CatedraEventoResumenDto;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class CatedraClient {
    private final WebClient webClient;
    private final CatedraProperties props;

    public CatedraClient(WebClient webClient, CatedraProperties props) {
        this.webClient = webClient;
        this.props = props;
    }

    public List<CatedraEventoResumenDto> listarEventosResumidos() {
        String url = props.getBaseUrl() + "/api/endpoints/v1/eventos-resumidos";
        CatedraEventoResumenDto[] body = webClient
                .get()
                .uri(url)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + props.getJwtToken())
                .retrieve()
                .bodyToMono(CatedraEventoResumenDto[].class)
                .onErrorResume(ex -> Mono.just(new CatedraEventoResumenDto[0]))
                .block();
        return Arrays.asList(body != null ? body : new CatedraEventoResumenDto[0]);
    }
}
