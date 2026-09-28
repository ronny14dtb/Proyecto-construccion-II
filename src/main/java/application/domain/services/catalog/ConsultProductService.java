package application.domain.services.catalog;

import application.domain.models.Product;
import application.domain.ports.in.ConsultProductUseCase;
import application.domain.ports.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ConsultProductService implements ConsultProductUseCase {
    private final ProductRepositoryPort productRepository;

    public ConsultProductService(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow();
    }
}