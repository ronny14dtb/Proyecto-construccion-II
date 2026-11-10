package application.domain.ports.out;

import application.domain.models.Product;
import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(String id);
}