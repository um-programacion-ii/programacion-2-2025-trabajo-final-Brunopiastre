package ar.edu.um.ticketflow.backend.client;

import ar.edu.um.ticketflow.backend.client.dto.EventoResumidoDto;
import ar.edu.um.ticketflow.backend.config.CatedraProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CatedraClientService {

    private final WebClient webClient;
    private final CatedraProperties props;

    public CatedraClientService(CatedraProperties props) {
        this.props = props;
        this.webClient = WebClient.builder()
                .baseUrl(props.getBaseUrl())
                .defaultHeader("Authorization", "Bearer " + props.getJwtToken())
                .build();
    }

    public List<EventoResumidoDto> obtenerEventosResumidos() {
        return webClient.get()
                .uri(props.getEndpoints().getEventosResumidos())
                .retrieve()
                .bodyToFlux(EventoResumidoDto.class)
                .collectList()
                .block();
    }
}
