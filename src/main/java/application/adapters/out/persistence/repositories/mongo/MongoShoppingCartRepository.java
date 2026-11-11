package application.adapters.out.persistence.repositories.mongo;

import application.adapters.out.persistence.entities.ShoppingCartDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoShoppingCartRepository extends MongoRepository<ShoppingCartDocument, String> {
}