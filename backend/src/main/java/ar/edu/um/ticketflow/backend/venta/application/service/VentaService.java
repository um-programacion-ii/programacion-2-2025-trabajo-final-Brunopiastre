package ar.edu.um.ticketflow.backend.venta.application.service;

// 1. IMPORT CORRECTO (Apuntando a tu carpeta .entity)
import ar.edu.um.ticketflow.backend.venta.domain.entity.Venta;
import ar.edu.um.ticketflow.backend.venta.infrastructure.repository.VentaRepository;
import ar.edu.um.ticketflow.backend.venta.infrastructure.controller.dto.CompraRequestDto;
import ar.edu.um.ticketflow.backend.venta.infrastructure.controller.dto.BloqueoRequestDto;
import ar.edu.um.ticketflow.backend.venta.infrastructure.controller.dto.BloqueoResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService {

  @Autowired
  private VentaRepository ventaRepository;

  public void procesarCompra(CompraRequestDto compraRequest) {
    System.out.println("🛒 Iniciando compra real para Usuario ID: " + compraRequest.getUsuarioId());

    // 1. Preparar datos para el Proxy
    List<BloqueoRequestDto.AsientoBloqueoDto> asientosParaProxy = compraRequest.getAsientos().stream()
      .map(a -> new BloqueoRequestDto.AsientoBloqueoDto(a.getFila(), a.getColumna()))
      .collect(Collectors.toList());

    // 2. LLAMADA REAL AL PROXY
    boolean bloqueoExitoso = llamarAlProxyParaBloquear(compraRequest.getEventoId(), asientosParaProxy);

    if (!bloqueoExitoso) {
      throw new RuntimeException("❌ La Cátedra rechazó el bloqueo. Asientos ocupados.");
    }

    // 3. PERSISTENCIA (Usando tu clase Venta corregida)
    Venta nuevaVenta = new Venta();
    nuevaVenta.setEventoId(compraRequest.getEventoId());
    nuevaVenta.setUsuarioId(compraRequest.getUsuarioId()); // Guardamos ID directo
    nuevaVenta.setFechaVenta(LocalDateTime.now());       // Tu campo se llama fechaVenta
    nuevaVenta.setCantidadAsientos(compraRequest.getAsientos().size());

    // Calculamos total (simulado 1500 por entrada)
    nuevaVenta.setTotal(1500.0 * compraRequest.getAsientos().size());

    ventaRepository.save(nuevaVenta);

    System.out.println("✅ Venta registrada en BD Local. ID: " + nuevaVenta.getId());
  }

  private boolean llamarAlProxyParaBloquear(Long eventoId, List<BloqueoRequestDto.AsientoBloqueoDto> asientos) {
    String proxyUrl = "http://localhost:8081/api/proxy/bloquear";
    BloqueoRequestDto request = new BloqueoRequestDto(eventoId, asientos);
    RestTemplate restTemplate = new RestTemplate();

    try {
      System.out.println("📡 Contactando Proxy en: " + proxyUrl);
      ResponseEntity<BloqueoResponseDto> response = restTemplate.postForEntity(proxyUrl, request, BloqueoResponseDto.class);

      if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
        return response.getBody().isResultado();
      }
      return false;
    } catch (Exception e) {
      System.err.println("❌ Error conectando al Proxy: " + e.getMessage());
      return false;
    }
  }
}
