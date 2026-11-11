package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.WarehouseEntity;
import application.domain.models.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehousePersistenceMapper {

    public WarehouseEntity toEntity(Warehouse warehouse) {
        if (warehouse == null) return null;

        WarehouseEntity entity = new WarehouseEntity();
        // entity.setWarehouseId(warehouse.getWarehouseId()); // Asegúrate de tener los getters
        // entity.setName(warehouse.getName());
        return entity;
    }

    public Warehouse toDomain(WarehouseEntity entity) {
        if (entity == null) return null;

        Warehouse warehouse = new Warehouse(); 
        // warehouse.setWarehouseId(entity.getWarehouseId());
        return warehouse;
    }
}