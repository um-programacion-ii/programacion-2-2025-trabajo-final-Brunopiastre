package ar.edu.um.ticketflow.backend.estado;

import ar.edu.um.ticketflow.backend.config.ProxyProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class EstadoAsientosService {

    private final WebClient webClient;
    private final ProxyProperties proxyProperties;

    public EstadoAsientosService(ProxyProperties proxyProperties) {
        this.proxyProperties = proxyProperties;
        this.webClient = WebClient.builder()
                .baseUrl(proxyProperties.getBaseUrl())
                .build();
    }

    public List<EstadoAsientoDto> obtenerEstadoAsientos(long eventoId) {
        return webClient.get()
                .uri("/api/proxy/eventos/" + eventoId + "/asientos")
                .retrieve()
                .bodyToFlux(EstadoAsientoDto.class)
                .collectList()
                .block();
    }
}
