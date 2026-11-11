package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.RefundEntity;
import application.domain.models.ReturnRequest;
import org.springframework.stereotype.Component;

@Component
public class RefundPersistenceMapper {

    public RefundEntity toEntity(ReturnRequest returnRequest) {
        if (returnRequest == null) return null;

        RefundEntity entity = new RefundEntity();
        entity.setRefundId(returnRequest.getReturnId());
        entity.setOrderId(returnRequest.getOrderId());
        entity.setReason(returnRequest.getReason());
        
        return entity;
    }

    public ReturnRequest toDomain(RefundEntity entity) {
        if (entity == null) return null;

        ReturnRequest returnRequest = new ReturnRequest(
            entity.getRefundId(),
            entity.getOrderId(),
            entity.getReason()
        );
        
        return returnRequest;
    }
}