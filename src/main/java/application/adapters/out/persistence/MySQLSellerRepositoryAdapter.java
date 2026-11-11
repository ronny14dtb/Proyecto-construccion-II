package application.adapters.out.persistence;

import application.adapters.out.persistence.mappers.SellerPersistenceMapper;
import application.adapters.out.persistence.repositories.jpa.SellerJpaRepository;
import application.domain.models.Seller;
import application.domain.ports.out.SellerRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MySQLSellerRepositoryAdapter implements SellerRepositoryPort {

    private final SellerJpaRepository jpaRepository;
    private final SellerPersistenceMapper mapper;

    public MySQLSellerRepositoryAdapter(SellerJpaRepository jpaRepository, SellerPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Seller save(Seller seller) {
        var entity = mapper.toEntity(seller);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Seller> findBySellerId(String sellerId) {
        return jpaRepository.findById(sellerId).map(mapper::toDomain);
    }
}