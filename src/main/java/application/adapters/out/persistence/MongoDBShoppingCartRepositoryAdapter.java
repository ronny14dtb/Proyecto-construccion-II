package application.adapters.out.persistence;

import application.adapters.out.persistence.entities.ShoppingCartDocument;
import application.adapters.out.persistence.mappers.ShoppingCartPersistenceMapper;
import application.adapters.out.persistence.repositories.mongo.ShoppingCartMongoRepository;
import application.domain.models.ShoppingCart;
import application.domain.ports.out.ShoppingCartRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MongoDBShoppingCartRepositoryAdapter implements ShoppingCartRepositoryPort {

    private final ShoppingCartMongoRepository mongoRepository;
    private final ShoppingCartPersistenceMapper mapper;

    public MongoDBShoppingCartRepositoryAdapter(ShoppingCartMongoRepository mongoRepository, ShoppingCartPersistenceMapper mapper) {
        this.mongoRepository = mongoRepository;
        this.mapper = mapper;
    }

    @Override
    public ShoppingCart save(ShoppingCart cart) {
        ShoppingCartDocument document = mapper.toDocument(cart);
        ShoppingCartDocument savedDocument = mongoRepository.save(document);
        return mapper.toDomain(savedDocument);
    }

    @Override
    public Optional<ShoppingCart> findById(String id) {
        return mongoRepository.findById(id).map(mapper::toDomain);
    }
}