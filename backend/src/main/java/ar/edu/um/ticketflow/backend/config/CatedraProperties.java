package ar.edu.um.ticketflow.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "catedra")
public class CatedraProperties {

    private String baseUrl;
    private String jwtToken;
    private Endpoints endpoints;

    public static class Endpoints {
        private String registrar;
        private String login;

        public String getRegistrar() { return registrar; }
        public void setRegistrar(String registrar) { this.registrar = registrar; }

        public String getLogin() { return login; }
        public void setLogin(String login) { this.login = login; }
    }

    public String getBaseUrl() { return baseUrl; }
    public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }

    public String getJwtToken() { return jwtToken; }
    public void setJwtToken(String jwtToken) { this.jwtToken = jwtToken; }

    public Endpoints getEndpoints() { return endpoints; }
    public void setEndpoints(Endpoints endpoints) { this.endpoints = endpoints; }
}
