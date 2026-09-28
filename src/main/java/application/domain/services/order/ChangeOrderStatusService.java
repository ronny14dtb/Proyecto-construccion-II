package application.domain.services.order;

import application.domain.enums.OrderStatus;
import application.domain.models.Order;
import application.domain.ports.in.ChangeOrderStatusUseCase;
import application.domain.ports.out.OrderRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ChangeOrderStatusService implements ChangeOrderStatusUseCase {
    
    private final OrderRepositoryPort orderRepository;

    public ChangeOrderStatusService(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order changeStatus(String orderId, OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("New status cannot be null");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderId));

        // Delegamos el cambio de estado al modelo (debes tener este método en tu modelo)
        order.changeStatus(status);

        return orderRepository.save(order);
    }
}