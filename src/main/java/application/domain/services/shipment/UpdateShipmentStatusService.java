package application.domain.services.shipment;

import application.domain.models.Shipment;
import application.domain.ports.in.UpdateShipmentStatusUseCase;
import application.domain.ports.out.ShipmentRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateShipmentStatusService implements UpdateShipmentStatusUseCase {
    private final ShipmentRepositoryPort shipmentRepository;

    public UpdateShipmentStatusService(ShipmentRepositoryPort shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Shipment updateStatus(String shipmentId, String status) {
        // Lógica de actualización pendiente
        return null;
    }
}