package application.adapters.out.persistence.repositories.jpa;

import application.adapters.out.persistence.entities.BuyerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerJpaRepository extends JpaRepository<BuyerEntity, Long> {
    
    // Agrega esta línea para que Spring Data sepa buscar por el identificador del comprador:
    Optional<BuyerEntity> findByBuyerId(String buyerId);
}