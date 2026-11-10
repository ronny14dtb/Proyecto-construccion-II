package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.BuyerEntity;
import application.domain.models.Buyer;
import application.domain.models.User;
import application.domain.valueobjects.Address;
import application.domain.valueobjects.Email;
import application.domain.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class BuyerPersistenceMapper {

    public Buyer toDomain(BuyerEntity entity) {
        if (entity == null) {
            return null;
        }
        

        User user = new User(
            "USR-" + (entity.getUserEmail() != null ? entity.getUserEmail().hashCode() : "DEFAULT"),
            "Usuario Registrado",
            new Email(entity.getUserEmail() != null ? entity.getUserEmail() : "correo@test.com"),
            UserRole.values()[0], // Toma el primer rol disponible del enum automáticamente
            "TEMPORARY_PASSWORD"
        );

        String details = entity.getPrimaryAddressDetails() != null ? entity.getPrimaryAddressDetails() : "Sin dirección";
        Address address = new Address(details, "", "", "");


        return new Buyer(
            entity.getBuyerId(),
            user,
            address
        );
    }

    public BuyerEntity toEntity(Buyer domain) {
        if (domain == null) {
            return null;
        }
        
        BuyerEntity entity = new BuyerEntity();
        entity.setBuyerId(domain.getBuyerId());
        
        if (domain.getUser() != null && domain.getUser().getEmail() != null) {
            // Usamos .toString() o el método que devuelva el string del email
            entity.setUserEmail(domain.getUser().getEmail().toString()); 
        }
        
        if (domain.getPrimaryAddress() != null) {
            entity.setPrimaryAddressDetails(domain.getPrimaryAddress().getStreet());
        }
        
        return entity;
    }
}