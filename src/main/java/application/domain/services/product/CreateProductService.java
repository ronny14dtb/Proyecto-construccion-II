package application.domain.services.product;

import application.domain.models.Product;
import application.domain.ports.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateProductService {

    private final ProductRepositoryPort productRepositoryPort;

    public CreateProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public Product execute(String sellerId, String name, double price, int stock) {
        String productId = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        Product product = new Product(productId, sellerId, name, price, stock);
        return productRepositoryPort.save(product);
    }
}