package application.domain.services.catalog;

import application.domain.models.Product;
import application.domain.ports.in.RegisterProductUseCase;
import application.domain.ports.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RegisterProductService implements RegisterProductUseCase {
    private final ProductRepositoryPort productRepository;

    public RegisterProductService(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product registerProduct(Product product) {
        return productRepository.save(product);
    }
}