package application.adapters.out.persistence;

import application.adapters.out.persistence.mappers.ProductPersistenceMapper;
import application.adapters.out.persistence.repositories.jpa.ProductJpaRepository;
import application.domain.models.Product;
import application.domain.ports.out.ProductRepositoryPort;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Primary
public class MySQLProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository jpaRepository;
    private final ProductPersistenceMapper mapper;

    public MySQLProductRepositoryAdapter(ProductJpaRepository jpaRepository, ProductPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {
        var entity = mapper.toEntity(product);
        var saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findByProductId(String productId) {
        return jpaRepository.findById(productId).map(mapper::toDomain);
    }

    @Override
    public List<Product> findBySellerId(String sellerId) {
        return jpaRepository.findBySellerId(sellerId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}