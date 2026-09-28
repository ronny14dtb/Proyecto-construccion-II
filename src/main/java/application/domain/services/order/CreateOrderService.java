package application.domain.services.order;

import application.domain.models.Order;
import application.domain.models.Buyer;
import application.domain.ports.in.CreateOrderUseCase;
import application.domain.ports.out.OrderRepositoryPort;
import application.domain.ports.out.BuyerRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateOrderService implements CreateOrderUseCase {
    
    private final OrderRepositoryPort orderRepository;
    private final BuyerRepositoryPort buyerRepository; // Agregamos el puerto del comprador

    public CreateOrderService(OrderRepositoryPort orderRepository, BuyerRepositoryPort buyerRepository) {
        this.orderRepository = orderRepository;
        this.buyerRepository = buyerRepository;
    }

    @Override
    public Order createOrder(String orderId, String buyerId) {
        if (buyerId == null || buyerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Buyer ID cannot be null or empty");
        }

        // 1. Buscamos el objeto Buyer real en la base de datos
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("Buyer not found with id: " + buyerId));

        // 2. Generamos el ID de la orden si no viene
        String finalOrderId = (orderId == null || orderId.trim().isEmpty()) ? UUID.randomUUID().toString() : orderId;

        // 3. Creamos la orden pasando el objeto Buyer completo (¡Adiós error en rojo!)
        Order newOrder = new Order(finalOrderId, buyer);

        return orderRepository.save(newOrder);
    }
}