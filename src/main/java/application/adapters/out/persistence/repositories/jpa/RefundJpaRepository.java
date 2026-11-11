package application.adapters.out.persistence.repositories.jpa;

import application.adapters.out.persistence.entities.RefundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundJpaRepository extends JpaRepository<RefundEntity, String> {
}