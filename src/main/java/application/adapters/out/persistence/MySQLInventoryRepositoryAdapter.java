package application.adapters.out.persistence;

import application.adapters.out.persistence.entities.InventoryEntity;
import application.adapters.out.persistence.mappers.InventoryPersistenceMapper;
import application.adapters.out.persistence.repositories.InventoryJpaRepository;
import application.domain.models.Inventory;
import application.domain.ports.out.InventoryRepositoryPort;

import java.util.Objects;
import java.util.Optional;

public class MySQLInventoryRepositoryAdapter implements InventoryRepositoryPort {

    private final InventoryJpaRepository jpaRepository;
    private final InventoryPersistenceMapper mapper;

    public MySQLInventoryRepositoryAdapter(InventoryJpaRepository jpaRepository, InventoryPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Inventory save(Inventory inventory) {
        InventoryEntity entity = mapper.toEntity(inventory);
        InventoryEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    public Optional<Inventory> findById(String id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Inventory> findByProductIdAndWarehouseId(String productId, String warehouseId) {
        return jpaRepository.findAll().stream()
            .filter(entity -> Objects.equals(entity.getProductId(), productId)
                && Objects.equals(entity.getWarehouseId(), warehouseId))
            .findFirst()
            .map(mapper::toDomain);
    }
}