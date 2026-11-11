package application.adapters.in.web;

import application.adapters.in.web.dto.ShipmentRequestDto;
import application.domain.models.Shipment;
import application.domain.ports.in.CreateShipmentUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final CreateShipmentUseCase createShipmentUseCase;

    public ShipmentController(CreateShipmentUseCase createShipmentUseCase) {
        this.createShipmentUseCase = createShipmentUseCase;
    }

    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody ShipmentRequestDto request) {
        Shipment shipment = createShipmentUseCase.execute(
                request.getOrderId(),
                request.getCarrier(),
                request.getTrackingNumber()
        );
        return ResponseEntity.ok(shipment);
    }
}