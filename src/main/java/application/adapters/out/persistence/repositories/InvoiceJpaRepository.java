package application.adapters.out.persistence.repositories;

import application.adapters.out.persistence.entities.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceJpaRepository extends JpaRepository<InvoiceEntity, String> {
}