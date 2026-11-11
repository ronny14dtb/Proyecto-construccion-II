package application.adapters.out.persistence.repositories.jpa;

import application.adapters.out.persistence.entities.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findByBuyerId(String buyerId);
}