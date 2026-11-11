package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.RefundEntity;
import application.domain.models.ReturnRequest;
import org.springframework.stereotype.Component;

@Component
public class RefundPersistenceMapper {

    // De la Base de Datos al Dominio
    public ReturnRequest toDomain(RefundEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ReturnRequest(
                String.valueOf(entity.getRefundId()), // O entity.getRefundId() si es String
                entity.getOrderId(),
                entity.getReason()
        );
    }

    // Del Dominio a la Base de Datos
    public RefundEntity toEntity(ReturnRequest domain) {
        if (domain == null) {
            return null;
        }
        RefundEntity entity = new RefundEntity();
        
        // Si en BD el ID es un Long (autoincrementable), puedes omitir setearlo al crear
        if (domain.getReturnId() != null) {
            // entity.setId(Long.valueOf(domain.getReturnId())); // Descomenta si tu ID en MySQL es numérico
            entity.setRefundId(domain.getReturnId()); // Úsalo así si tu ID en MySQL es String
        }
        
        entity.setOrderId(domain.getOrderId());
        entity.setReason(domain.getReason());
        
        return entity;
    }
}