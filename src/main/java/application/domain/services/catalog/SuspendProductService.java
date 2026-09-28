package application.domain.services.catalog;

import application.domain.models.Product;
import application.domain.ports.in.SuspendProductUseCase;
import application.domain.ports.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class SuspendProductService implements SuspendProductUseCase {
    
    private final ProductRepositoryPort productRepository;

    public SuspendProductService(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

  @Override
    public void suspendProduct(String id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));

        product.suspendProduct(); 

  
        productRepository.save(product);
    }
}