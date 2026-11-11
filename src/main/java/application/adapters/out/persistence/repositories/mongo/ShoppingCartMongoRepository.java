package application.adapters.out.persistence.repositories.mongo;

import application.adapters.out.persistence.entities.ShoppingCartDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingCartMongoRepository extends MongoRepository<ShoppingCartDocument, String> {
}