package application.adapters.out.persistence.repositories;

import application.adapters.out.persistence.entities.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryJpaRepository extends JpaRepository<InventoryEntity, String> {
}