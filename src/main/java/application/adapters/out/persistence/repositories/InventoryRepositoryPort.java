package application.adapters.out.persistence.repositories;

import application.domain.models.Inventory;
import java.util.Optional;

public interface InventoryRepositoryPort {
    Inventory save(Inventory inventory);
    Optional<Inventory> findById(String id);
}