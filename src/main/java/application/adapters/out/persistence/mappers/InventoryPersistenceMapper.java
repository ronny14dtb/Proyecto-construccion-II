package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.InventoryEntity;
import application.domain.models.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryPersistenceMapper {

    public InventoryEntity toEntity(Inventory inventory) {
        if (inventory == null) return null;

        InventoryEntity entity = new InventoryEntity();
        entity.setInventoryId(inventory.getInventoryId());
        entity.setProductId(inventory.getProduct() != null ? inventory.getProduct().getProductId() : null);
        entity.setWarehouseId(null); // Se asociará cuando implementes Warehouse por completo
        entity.setAvailableQuantity(inventory.getAvailableQuantity());
        entity.setReservedQuantity(inventory.getReservedQuantity());

        return entity;
    }

    public Inventory toDomain(InventoryEntity entity) {
        if (entity == null) return null;

        // Instanciamos con null en Product y Warehouse temporalmente para cumplir el constructor del dominio
        Inventory inventory = new Inventory(
            entity.getInventoryId(),
            null, // Product
            null, // Warehouse
            entity.getAvailableQuantity()
        );
        
        return inventory;
    }
}