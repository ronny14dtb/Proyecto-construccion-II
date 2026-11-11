package application.domain.services.product;

import application.domain.models.Product;
import application.domain.ports.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsBySellerService {

    private final ProductRepositoryPort productRepositoryPort;

    public GetProductsBySellerService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public List<Product> execute(String sellerId) {
        return productRepositoryPort.findBySellerId(sellerId);
    }
}