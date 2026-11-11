package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.SellerEntity;
import application.domain.models.Seller;
import org.springframework.stereotype.Component;

@Component
public class SellerPersistenceMapper {

    public SellerEntity toEntity(Seller seller) {
        return new SellerEntity(
                seller.getSellerId(),
                seller.getStoreName(),
                seller.getEmail(),
                seller.getStatus()
        );
    }

    public Seller toDomain(SellerEntity entity) {
        return new Seller(
                entity.getSellerId(),
                entity.getStoreName(),
                entity.getEmail(),
                entity.getStatus()
        );
    }
}