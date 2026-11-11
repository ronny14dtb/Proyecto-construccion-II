package application.adapters.out.persistence;

import application.adapters.out.persistence.entities.WarehouseEntity;
import application.adapters.out.persistence.mappers.WarehousePersistenceMapper;
import application.adapters.out.persistence.repositories.jpa.WarehouseJpaRepository;
import application.domain.models.Warehouse;
import application.domain.ports.out.WarehouseRepositoryPort;

import java.util.Optional;

public class MySQLWarehouseRepositoryAdapter implements WarehouseRepositoryPort {

    private final WarehouseJpaRepository jpaRepository;
    private final WarehousePersistenceMapper mapper;

    public MySQLWarehouseRepositoryAdapter(WarehouseJpaRepository jpaRepository, WarehousePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        WarehouseEntity entity = mapper.toEntity(warehouse);
        WarehouseEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Warehouse> findById(String id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}