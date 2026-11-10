package application.adapters.out.persistence.repositories;

import application.adapters.out.persistence.entities.BuyerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerJpaRepository extends JpaRepository<BuyerEntity, Long> {
}