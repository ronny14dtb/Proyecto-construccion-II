package application.adapters.out.persistence.repositories.jpa;

import application.adapters.out.persistence.entities.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentJpaRepository extends JpaRepository<ShipmentEntity, String> {
}