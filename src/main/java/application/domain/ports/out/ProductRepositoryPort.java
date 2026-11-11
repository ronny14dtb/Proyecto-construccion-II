package application.domain.ports.out;

import application.domain.models.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findByProductId(String productId);
    List<Product> findBySellerId(String sellerId);
}