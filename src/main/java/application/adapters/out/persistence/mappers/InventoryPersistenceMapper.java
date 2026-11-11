package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.InventoryEntity;
import application.domain.models.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryPersistenceMapper {

    public InventoryEntity toEntity(Inventory inventory) {
        return new InventoryEntity(
                inventory.getInventoryId(),
                inventory.getProductId(),
                inventory.getSellerId(),
                inventory.getAvailableQuantity(),
                0, // reservedQuantity por defecto en el mapeo
                inventory.getWarehouseLocation()
        );
    }

    public Inventory toDomain(InventoryEntity entity) {
        return new Inventory(
                entity.getInventoryId(),
                entity.getProductId(),
                entity.getSellerId(),
                entity.getAvailableQuantity(),
                entity.getWarehouseLocation()
        );
    }
}