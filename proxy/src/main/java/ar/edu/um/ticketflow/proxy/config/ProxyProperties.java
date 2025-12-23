package ar.edu.um.ticketflow.proxy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "catedra")
public class ProxyProperties {
    /** Base URL of the cátedra API, e.g. http://192.168.194.250:8080 */
    private String baseUrl;
    /** Username for /api/authenticate */
    private String username;
    /** Password for /api/authenticate */
    private String password;

    /** Backend callback URL to notify updates, e.g. http://localhost:8080/internal/proxy/eventos/updated */
    private String backendCallbackUrl;
    /** Internal token header value to send to backend */
    private String backendInternalToken;

    public String getBaseUrl() { return baseUrl; }
    public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getBackendCallbackUrl() { return backendCallbackUrl; }
    public void setBackendCallbackUrl(String backendCallbackUrl) { this.backendCallbackUrl = backendCallbackUrl; }
    public String getBackendInternalToken() { return backendInternalToken; }
    public void setBackendInternalToken(String backendInternalToken) { this.backendInternalToken = backendInternalToken; }
}
