package application.domain.services.order;

import application.domain.models.Order;
import application.domain.models.OrderItem;
import application.domain.ports.in.AddItemToOrderUseCase;
import application.domain.ports.out.OrderRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class AddItemToOrderService implements AddItemToOrderUseCase {
    
    private final OrderRepositoryPort orderRepository;

    public AddItemToOrderService(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order addItem(String orderId, OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item to add cannot be null");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderId));

        // Delegamos la regla de negocio al modelo Order (debes tener este método en tu modelo)
        order.addItem(item);

        return orderRepository.save(order);
    }
}