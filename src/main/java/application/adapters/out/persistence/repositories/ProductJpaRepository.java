package application.adapters.out.persistence.repositories;

import application.adapters.out.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, String> {
    // Spring Data JPA se encarga de implementar los métodos básicos (save, findById, etc.)
}