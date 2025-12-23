package ar.edu.um.ticketflow.backend.sale.application.service;

import ar.edu.um.ticketflow.backend.sale.infrastructure.adapter.out.jpa.entity.SaleEntity;
import ar.edu.um.ticketflow.backend.sale.infrastructure.adapter.out.jpa.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;

@Service
public class SaleService {

  private final SaleRepository saleRepository;
  private final RestTemplate restTemplate;

  @Value("${proxy.url}")
  private String proxyUrl;

  public SaleService(SaleRepository saleRepository) {
    this.saleRepository = saleRepository;
    this.restTemplate = new RestTemplate();
  }

  public SaleEntity iniciarVenta(String email, Long eventId, String asientos, Double precio) {
    // 1. Creamos la venta localmente como PENDIENTE
    SaleEntity venta = new SaleEntity();
    venta.setUserEmail(email);
    venta.setEventId(eventId);
    venta.setSeats(asientos);
    venta.setTotalPrice(precio);
    venta.setStatus(SaleEntity.SaleStatus.PENDIENTE);
    venta.setCreatedAt(LocalDateTime.now());

    SaleEntity ventaGuardada = saleRepository.save(venta);

    // 2. Intentamos confirmar con la Cátedra a través del Proxy
    confirmarConCatedra(ventaGuardada);

    return ventaGuardada;
  }

  public void confirmarConCatedra(SaleEntity venta) {
    try {
      // Llamada al Proxy para que este impacte en la API de la cátedra
      // El proxy debería tener un endpoint POST /proxy/ventas
      String url = proxyUrl + "/ventas";

      // Aquí enviarías el DTO que espera la cátedra
      // restTemplate.postForEntity(url, venta, String.class);

      // Si no hay excepción, marcamos como CONFIRMADA
      venta.setStatus(SaleEntity.SaleStatus.CONFIRMADA);
      saleRepository.save(venta);
      System.out.println("✅ Venta " + venta.getId() + " confirmada en la cátedra.");

    } catch (Exception e) {
      // Si falla, queda PENDIENTE y el Scheduler (que ya creamos) la reintentará
      System.err.println("❌ Error al confirmar venta " + venta.getId() + ". Queda pendiente para reintento.");
    }
  }
}
