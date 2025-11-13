package ar.edu.um.ticketflow.backend.session;

public record SessionData(
        String username,
        String token,
        long createdAt
) {}
