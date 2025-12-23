package ar.edu.um.ticketflow.backend.security;

import ar.edu.um.ticketflow.backend.config.ProxyProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class BackendInternalAuthFilter extends OncePerRequestFilter {

    private final ProxyProperties proxyProperties;

    public BackendInternalAuthFilter(ProxyProperties proxyProperties) {
        this.proxyProperties = proxyProperties;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getRequestURI();

        // Solo proteger rutas internas
        if (path.startsWith("/api/backend/internal")) {
            String auth = request.getHeader("Authorization");

            if (auth == null || !auth.startsWith("Bearer ")) {
                response.sendError(401, "Missing or invalid token");
                return;
            }

            String token = auth.substring(7);

            if (!token.equals(proxyProperties.getInternalToken())) {
                response.sendError(401, "Unauthorized");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
