package ar.edu.um.ticketflow.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "catedra")
public class CatedraProperties {

  private Api api;
  private Endpoints endpoints;

  // --- Getters y Setters de nivel superior ---

  public Api getApi() {
    return api;
  }

  public void setApi(Api api) {
    this.api = api;
  }

  public Endpoints getEndpoints() {
    return endpoints;
  }

  public void setEndpoints(Endpoints endpoints) {
    this.endpoints = endpoints;
  }

  // =========================================================
  // --- CLASE ANIDADA 1: Api --- (No cambia)
  // =========================================================

  public static class Api {
    private String url;
    private String token;

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getToken() {
      return token;
    }

    public void setToken(String token) {
      this.token = token;
    }
  }

  // =========================================================
  // --- CLASE ANIDADA 2: Endpoints --- (CORREGIDA)
  // =========================================================

  public static class Endpoints {

    // CORREGIDO: Propiedad necesaria para getLogin()
    private String login;

    // CORREGIDO: Propiedad necesaria para getEventosResumidos()
    private String eventosResumidos;

    // --- Getters y Setters para Endpoints ---

    public String getLogin() {
      return login;
    }

    public void setLogin(String login) {
      this.login = login;
    }

    public String getEventosResumidos() {
      return eventosResumidos;
    }

    public void setEventosResumidos(String eventosResumidos) {
      this.eventosResumidos = eventosResumidos;
    }
  }
}
