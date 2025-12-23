package ar.edu.um.ticketflow.backend.compra;

import ar.edu.um.ticketflow.backend.compra.dto.CompraRequest;
import ar.edu.um.ticketflow.backend.compra.dto.CompraResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/backend/compras")
public class CompraController {

    private final CompraService service;

    public CompraController(CompraService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CompraResponse> realizarCompra(@RequestBody CompraRequest request) {
        CompraResponse response = service.realizarCompra(request);
        return ResponseEntity.ok(response);
    }
}
