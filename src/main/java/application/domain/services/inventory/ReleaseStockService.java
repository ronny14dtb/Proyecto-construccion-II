package application.domain.services.inventory;

import application.domain.exceptions.InsufficientInventoryException;
import application.domain.models.Inventory;
import application.domain.ports.in.ReleaseStockUseCase;
import application.domain.ports.out.InventoryRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ReleaseStockService implements ReleaseStockUseCase {
    private final InventoryRepositoryPort inventoryRepository;

    public ReleaseStockService(InventoryRepositoryPort inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void releaseStock(String productId, String warehouseId, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than zero");
        Inventory inventory = inventoryRepository.findByProductIdAndWarehouseId(productId, warehouseId)
                .orElseThrow(() -> new InsufficientInventoryException("Inventory record not found for product: " + productId));
        inventory.releaseReservedStock(quantity);
        inventoryRepository.save(inventory);
    }
}