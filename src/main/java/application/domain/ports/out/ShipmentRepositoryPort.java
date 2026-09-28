package application.domain.ports.out;

import application.domain.models.Shipment;
import java.util.Optional;

public interface ShipmentRepositoryPort {
    Shipment save(Shipment shipment);
    Optional<Shipment> findById(String id);
}