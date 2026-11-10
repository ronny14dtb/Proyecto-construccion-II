package application.adapters.out.persistence;

import application.adapters.out.persistence.entities.ProductEntity;
import application.adapters.out.persistence.mappers.ProductPersistenceMapper;
import application.adapters.out.persistence.repositories.ProductJpaRepository;
import application.domain.models.Product;
import application.domain.ports.out.ProductRepositoryPort;

import java.util.Optional;

public class MySQLProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository jpaRepository;
    private final ProductPersistenceMapper mapper;

    public MySQLProductRepositoryAdapter(ProductJpaRepository jpaRepository, ProductPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = mapper.toEntity(product);
        ProductEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(String id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    // Si la interfaz ProductRepositoryPort tiene otros métodos (como findAll, etc.),
    // te pedirá que los agregues aquí. Si no, con esto es suficiente.
}