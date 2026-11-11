package application.adapters.out.persistence;

import application.adapters.out.persistence.entities.OrderEntity;
import application.adapters.out.persistence.mappers.OrderPersistenceMapper;
import application.adapters.out.persistence.repositories.jpa.OrderJpaRepository;
import application.domain.models.Order;
import application.domain.ports.out.OrderRepositoryPort;

import java.util.Optional;

public class MySQLOrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository jpaRepository;
    private final OrderPersistenceMapper mapper;

    public MySQLOrderRepositoryAdapter(OrderJpaRepository jpaRepository, OrderPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = mapper.toEntity(order);
        OrderEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Order> findById(String id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}