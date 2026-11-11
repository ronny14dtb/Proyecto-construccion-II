package application.adapters.out.persistence;

import application.adapters.out.persistence.entities.SellerEntity;
import application.adapters.out.persistence.mappers.SellerPersistenceMapper;
import application.adapters.out.persistence.repositories.jpa.SellerJpaRepository;
import application.domain.models.Seller;
import application.domain.ports.out.SellerRepositoryPort;

import java.util.Optional;

public class MySQLSellerRepositoryAdapter implements SellerRepositoryPort {

    private final SellerJpaRepository jpaRepository;
    private final SellerPersistenceMapper mapper;

    public MySQLSellerRepositoryAdapter(SellerJpaRepository jpaRepository, SellerPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Seller save(Seller seller) {
        SellerEntity entity = mapper.toEntity(seller);
        SellerEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Seller> findById(String id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}