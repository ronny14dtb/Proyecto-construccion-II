package application.adapters.out.persistence.repositories.jpa;

import application.adapters.out.persistence.entities.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerJpaRepository extends JpaRepository<SellerEntity, String> {
}