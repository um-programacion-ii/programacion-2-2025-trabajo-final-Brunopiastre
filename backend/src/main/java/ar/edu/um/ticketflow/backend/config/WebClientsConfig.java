package ar.edu.um.ticketflow.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientsConfig {
    @Bean
    public WebClient webClient() {
        HttpClient client = HttpClient.create();
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();
    }
}
