package application.adapters.out.persistence.repositories.jpa;

import application.adapters.out.persistence.entities.RefundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundJpaRepository extends JpaRepository<RefundEntity, String> {
    List<RefundEntity> findByOrderId(String orderId);
}