package application.domain.services.shipment;

import application.domain.models.Shipment;
import application.domain.ports.in.CreateShipmentUseCase;
import application.domain.ports.out.ShipmentRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateShipmentService implements CreateShipmentUseCase {
    private final ShipmentRepositoryPort shipmentRepository;

    public CreateShipmentService(ShipmentRepositoryPort shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Shipment createShipment(String orderId, String address) {
        // Lógica de creación pendiente
        return null; 
    }
}