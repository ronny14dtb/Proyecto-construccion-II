package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.SellerEntity;
import application.domain.models.Seller;
import org.springframework.stereotype.Component;

@Component
public class SellerPersistenceMapper {

   public SellerEntity toEntity(Seller seller) {
        if (seller == null) return null;

        SellerEntity entity = new SellerEntity();
        entity.setSellerId(seller.getSellerId()); 
        return entity;
    }

    public Seller toDomain(SellerEntity entity) {
        if (entity == null) return null;

        Seller seller = new Seller(); 
        seller.setSellerId(entity.getSellerId());
        return seller;
    }
}