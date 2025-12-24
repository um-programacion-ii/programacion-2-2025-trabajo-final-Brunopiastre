package ar.edu.um.ticketflow.backend.user.infrastructure.web.controller;

import ar.edu.um.ticketflow.backend.config.JwtService;
import ar.edu.um.ticketflow.backend.user.infrastructure.web.dto.AuthenticationRequest;
import ar.edu.um.ticketflow.backend.user.infrastructure.web.dto.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final JwtService jwtService;

  public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtService jwtService) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtService = jwtService;
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
    // 1. Validar usuario y contraseña (si falla, lanza excepción automáticamente)
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );

    // 2. Si llegamos aquí, es válido. Generamos el token.
    final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
    final String jwt = jwtService.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
}
