package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.ShipmentEntity;
import application.domain.models.Shipment;
import org.springframework.stereotype.Component;

@Component
public class ShipmentPersistenceMapper {

    public ShipmentEntity toEntity(Shipment shipment) {
        if (shipment == null) return null;
        ShipmentEntity entity = new ShipmentEntity();
        entity.setShipmentId(shipment.getShipmentId());
        entity.setOrderId(shipment.getOrderId());
        entity.setCarrier(shipment.getCarrier());
        entity.setTrackingNumber(shipment.getTrackingNumber());
        entity.setStatus(shipment.getStatus());
        return entity;
    }

    public Shipment toDomain(ShipmentEntity entity) {
        if (entity == null) return null;
        return new Shipment(
                entity.getShipmentId(),
                entity.getOrderId(),
                entity.getCarrier(),
                entity.getTrackingNumber(),
                entity.getStatus()
        );
    }
}