package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.ShipmentEntity;
import application.domain.models.Shipment;
import org.springframework.stereotype.Component;

@Component
public class ShipmentPersistenceMapper {

    public ShipmentEntity toEntity(Shipment shipment) {
        if (shipment == null) return null;

        ShipmentEntity entity = new ShipmentEntity();
        // entity.setShipmentId(shipment.getShipmentId()); // Ajusta según tu modelo
        // entity.setOrderId(shipment.getOrderId());
        return entity;
    }

    public Shipment toDomain(ShipmentEntity entity) {
        if (entity == null) return null;

        Shipment shipment = new Shipment(); 
        // shipment.setShipmentId(entity.getShipmentId());
        return shipment;
    }
}