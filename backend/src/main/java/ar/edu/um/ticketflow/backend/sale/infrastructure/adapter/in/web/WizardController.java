package ar.edu.um.ticketflow.backend.sale.infrastructure.adapter.in.web;

import ar.edu.um.ticketflow.backend.sale.application.service.dto.WizardState;
import java.time.Duration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wizard")
public class WizardController {

  private final RedisTemplate<String, Object> redisTemplate;

  public WizardController(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  // 1. Guardar el progreso (se llama al pasar de pantalla)
  @PostMapping("/save")
  public ResponseEntity<Void> guardarProgreso(@RequestBody WizardState estado) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    // Guardamos con una clave única por usuario
    redisTemplate.opsForValue().set("wizard:" + email, estado, Duration.ofMinutes(30));
    return ResponseEntity.ok().build();
  }

  // 2. Recuperar el progreso (se llama apenas el usuario hace Login)
  @GetMapping("/resume")
  public ResponseEntity<WizardState> recuperarProgreso() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    WizardState estado = (WizardState) redisTemplate.opsForValue().get("wizard:" + email);

    if (estado == null) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(estado);
  }

  // 3. Limpiar (al confirmar la venta o cerrar sesión)
  @DeleteMapping("/clear")
  public ResponseEntity<Void> limpiarProgreso() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    redisTemplate.delete("wizard:" + email);
    return ResponseEntity.ok().build();
  }
}
