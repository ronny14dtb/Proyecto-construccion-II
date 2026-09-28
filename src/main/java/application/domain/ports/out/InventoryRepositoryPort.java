package application.domain.ports.out;

import application.domain.models.Inventory;
import java.util.Optional;

public interface InventoryRepositoryPort {
    Inventory save(Inventory inventory);
    Optional<Inventory> findByProductIdAndWarehouseId(String productId, String warehouseId);
}