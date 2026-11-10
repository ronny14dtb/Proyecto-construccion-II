package application.adapters.out.persistence;

import application.adapters.out.persistence.entities.BuyerEntity;
import application.adapters.out.persistence.mappers.BuyerPersistenceMapper;
import application.adapters.out.persistence.repositories.BuyerJpaRepository;
import application.domain.models.Buyer;
import application.domain.ports.out.BuyerRepositoryPort;

import java.util.Optional;

public class MySQLBuyerRepositoryAdapter implements BuyerRepositoryPort {

    private final BuyerJpaRepository jpaRepository;
    private final BuyerPersistenceMapper mapper;

    public MySQLBuyerRepositoryAdapter(BuyerJpaRepository jpaRepository, BuyerPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Buyer save(Buyer buyer) {
        BuyerEntity entity = mapper.toEntity(buyer);
        BuyerEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Buyer> findById(String id) {
        return jpaRepository.findById(Long.valueOf(id)).map(mapper::toDomain);
    }

 
}