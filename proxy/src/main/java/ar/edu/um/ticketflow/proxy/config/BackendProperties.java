package ar.edu.um.ticketflow.proxy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "backend")
public class BackendProperties {
    /**
     * URL base del backend.
     * Al llamarse 'baseUrl', Spring buscará 'backend.base-url' y Lombok generará 'getBaseUrl()'
     */
    private String baseUrl;

    /**
     * Token para comunicación interna segura
     */
    private String internalToken;
}
