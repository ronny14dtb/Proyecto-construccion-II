package application.adapters.out.persistence.repositories.jpa;

import application.adapters.out.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, String> {
    List<ProductEntity> findBySellerId(String sellerId);
}