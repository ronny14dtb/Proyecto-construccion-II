package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.ProductEntity;
import application.domain.models.Product;
import application.domain.models.Seller;
import application.domain.valueobjects.Money;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceMapper {

    public ProductEntity toEntity(Product product) {
        if (product == null) return null;
        
        ProductEntity entity = new ProductEntity();
        entity.setProductId(product.getProductId());
        entity.setName(product.getName());
        entity.setProductType(product.getProductType());
        entity.setStatus(product.getStatus());
        
        // Nota: Si en el futuro le agregas a tu Product el método getDescription(), 
        // puedes descomentar esta línea:
        // entity.setDescription(product.getDescription());
        
        return entity;
    }

    public Product toDomain(ProductEntity entity) {
        if (entity == null) return null;
        
        // Usamos null temporalmente en Money y Seller para que compile rápido hoy.
        // Más adelante, cuando integres esas tablas, cambias estos null por los objetos reales.
        Product product = new Product(
            entity.getProductId(),
            entity.getName(),
            null, // basePrice (Money)
            entity.getProductType(),
            null  // seller (Seller)
        );
        
        // Si el estado viene suspendido desde la BD, lo actualizamos
        if (entity.getStatus() != null && entity.getStatus().name().equals("SUSPENDIDO")) {
            product.suspendProduct();
        }
        
        return product;
    }
}