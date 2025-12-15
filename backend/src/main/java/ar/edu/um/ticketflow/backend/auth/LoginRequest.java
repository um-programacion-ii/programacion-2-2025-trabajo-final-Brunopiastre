package ar.edu.um.ticketflow.backend.auth;

public record LoginRequest(String username, String password, boolean rememberMe) {}
