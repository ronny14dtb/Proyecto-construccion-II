package application.adapters.in.web;

import application.adapters.in.web.dto.ProductRequestDto;
import application.domain.models.Product;
import application.domain.services.product.CreateProductService;
import application.domain.services.product.GetProductsBySellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductService createProductService;
    private final GetProductsBySellerService getProductsBySellerService;

    public ProductController(CreateProductService createProductService, GetProductsBySellerService getProductsBySellerService) {
        this.createProductService = createProductService;
        this.getProductsBySellerService = getProductsBySellerService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDto request) {
        Product product = createProductService.execute(
                request.getSellerId(),
                request.getName(),
                request.getPrice(),
                request.getStock()
        );
        return ResponseEntity.ok(product);
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Product>> getProductsBySeller(@PathVariable String sellerId) {
        List<Product> products = getProductsBySellerService.execute(sellerId);
        return ResponseEntity.ok(products);
    }
}