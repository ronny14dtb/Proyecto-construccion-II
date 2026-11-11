package application.adapters.out.persistence.repositories.jpa;

import application.adapters.out.persistence.entities.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseJpaRepository extends JpaRepository<WarehouseEntity, String> {
}