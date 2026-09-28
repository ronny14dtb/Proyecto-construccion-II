package application.domain.ports.out;

import application.domain.models.Product;
import java.util.Optional;
import java.util.List;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(String id);
    List<Product> findAllActive();
}