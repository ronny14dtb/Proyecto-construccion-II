package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.ProductEntity;
import application.domain.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceMapper {

    public ProductEntity toEntity(Product product) {
        return new ProductEntity(
                product.getProductId(),
                product.getSellerId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }

    public Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getProductId(),
                entity.getSellerId(),
                entity.getName(),
                entity.getPrice(),
                entity.getStock()
        );
    }
}