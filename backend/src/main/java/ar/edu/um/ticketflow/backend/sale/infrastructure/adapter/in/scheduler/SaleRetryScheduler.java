package ar.edu.um.ticketflow.backend.sale.infrastructure.adapter.in.scheduler;

import ar.edu.um.ticketflow.backend.sale.application.service.SaleService;
import ar.edu.um.ticketflow.backend.sale.infrastructure.adapter.out.jpa.entity.SaleEntity;
import ar.edu.um.ticketflow.backend.sale.infrastructure.adapter.out.jpa.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaleRetryScheduler {

  private final Logger log = LoggerFactory.getLogger(SaleRetryScheduler.class);
  private final SaleRepository saleRepository;
  private final SaleService saleService;

  public SaleRetryScheduler(SaleRepository saleRepository, SaleService saleService) {
    this.saleRepository = saleRepository;
    this.saleService = saleService;
  }

  // Se ejecuta cada 1 minuto (60000 milisegundos)
  @Scheduled(fixedRate = 60000)
  public void reintentarVentasPendientes() {
    // 1. Buscamos en nuestra DB local todas las que no se confirmaron
    List<SaleEntity> pendientes = saleRepository.findByStatus(SaleEntity.SaleStatus.PENDIENTE);

    if (pendientes.isEmpty()) {
      return; // Nada para hacer, volvemos a dormir
    }

    log.info("🔄 SCHEDULER: Se encontraron {} ventas pendientes de confirmación.", pendientes.size());

    for (SaleEntity venta : pendientes) {
      log.info("⏳ Reintentando confirmar venta ID: {}...", venta.getId());

      // 2. Llamamos al método que ya tenés en el service que habla con el Proxy
      saleService.confirmarConCatedra(venta);
    }
  }
}
