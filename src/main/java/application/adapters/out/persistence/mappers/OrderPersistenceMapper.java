package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.OrderEntity;
import application.domain.models.Order;
import application.domain.models.Buyer;
import application.domain.enums.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderPersistenceMapper {

    public OrderEntity toEntity(Order order) {
        if (order == null) return null;

        OrderEntity entity = new OrderEntity();
        entity.setOrderId(order.getOrderId());
        
        if (order.getBuyer() != null) {
            // Revisa si en tu clase Buyer el método para obtener el ID es getBuyerId() o getId()
            entity.setBuyerId(order.getBuyer().getBuyerId()); 
        }
        
        if (order.getStatus() != null) {
            entity.setStatus(order.getStatus().name());
        }
        
        return entity;
    }
public Order toDomain(OrderEntity entity) {
        if (entity == null) return null;

        Buyer buyer = new Buyer();
        buyer.setBuyerId(entity.getBuyerId());

        Order order = new Order(entity.getOrderId(), buyer);
        
        if (entity.getStatus() != null) {
            order.changeStatus(OrderStatus.valueOf(entity.getStatus()));
        }
        
        return order;
    }
}