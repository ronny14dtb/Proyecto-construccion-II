package application.adapters.out.persistence;

import application.adapters.out.persistence.mappers.InventoryPersistenceMapper;
import application.adapters.out.persistence.repositories.jpa.InventoryJpaRepository;
import application.domain.models.Inventory;
import application.domain.ports.out.InventoryRepositoryPort;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Primary
@Repository
public class MySQLInventoryRepositoryAdapter implements InventoryRepositoryPort {

    private final InventoryJpaRepository jpaRepository;
    private final InventoryPersistenceMapper mapper;

    public MySQLInventoryRepositoryAdapter(InventoryJpaRepository jpaRepository, InventoryPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Inventory save(Inventory inventory) {
        var entity = mapper.toEntity(inventory);
        var saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Inventory> findByProductId(String productId) {
        return jpaRepository.findByProductId(productId).map(mapper::toDomain);
    }
}