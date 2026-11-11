package application.domain.services.inventory;

import application.domain.models.Inventory;
import application.domain.ports.out.InventoryRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ManageInventoryService {

    private final InventoryRepositoryPort inventoryRepositoryPort;

    public ManageInventoryService(InventoryRepositoryPort inventoryRepositoryPort) {
        this.inventoryRepositoryPort = inventoryRepositoryPort;
    }

    public Inventory registerOrUpdateStock(String productId, String sellerId, int quantity, String location) {
        Inventory inventory = inventoryRepositoryPort.findByProductId(productId)
                .map(existing -> {
                    existing.updateStock(quantity);
                    return existing;
                })
                .orElseGet(() -> new Inventory(
                        "inv-" + UUID.randomUUID().toString().substring(0, 8),
                        productId,
                        sellerId,
                        quantity,
                        location
                ));

        return inventoryRepositoryPort.save(inventory);
    }
}