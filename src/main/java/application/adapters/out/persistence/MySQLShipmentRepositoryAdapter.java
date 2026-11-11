package application.adapters.out.persistence;

import application.adapters.out.persistence.entities.ShipmentEntity;
import application.adapters.out.persistence.mappers.ShipmentPersistenceMapper;
import application.adapters.out.persistence.repositories.jpa.ShipmentJpaRepository;
import application.domain.models.Shipment;
import application.domain.ports.out.ShipmentRepositoryPort;

import java.util.Optional;

public class MySQLShipmentRepositoryAdapter implements ShipmentRepositoryPort {

    private final ShipmentJpaRepository jpaRepository;
    private final ShipmentPersistenceMapper mapper;

    public MySQLShipmentRepositoryAdapter(ShipmentJpaRepository jpaRepository, ShipmentPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Shipment save(Shipment shipment) {
        ShipmentEntity entity = mapper.toEntity(shipment);
        ShipmentEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Shipment> findById(String id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}