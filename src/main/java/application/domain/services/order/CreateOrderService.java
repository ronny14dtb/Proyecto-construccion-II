package application.domain.services.order;

import application.domain.models.Order;
import application.domain.models.Buyer;
import application.domain.models.ShoppingCart;
import application.domain.ports.in.CreateOrderUseCase;
import application.domain.ports.out.OrderRepositoryPort;
import application.domain.ports.out.BuyerRepositoryPort;
import application.domain.ports.out.ShoppingCartRepositoryPort; // Puerto de Mongo
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CreateOrderService implements CreateOrderUseCase {
    
    private final OrderRepositoryPort orderRepository;
    private final BuyerRepositoryPort buyerRepository;
    private final ShoppingCartRepositoryPort shoppingCartRepository;

    public CreateOrderService(OrderRepositoryPort orderRepository, 
                              BuyerRepositoryPort buyerRepository,
                              ShoppingCartRepositoryPort shoppingCartRepository) {
        this.orderRepository = orderRepository;
        this.buyerRepository = buyerRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    @Transactional
    public Order createOrder(String orderId, String buyerId) {
        if (buyerId == null || buyerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Buyer ID cannot be null or empty");
        }

        // 1. Convertimos a Long de forma segura para MySQL
        Long idLong;
        try {
            idLong = Long.valueOf(buyerId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Buyer ID must be a valid number: " + buyerId);
        }

        // 2. Creación perezosa del comprador si no existe
      Buyer buyer = buyerRepository.findByBuyerId(buyerId)
        .orElseGet(() -> {
            System.out.println("Comprador no encontrado. Creando registro automático para: " + buyerId);
            Buyer newBuyer = new Buyer(buyerId); 
            return buyerRepository.save(newBuyer);
        });

        // 3. Consultamos el carrito en MongoDB usando el puerto
        ShoppingCart cart = shoppingCartRepository.findById(buyerId)
        .orElse(null); // Si no tiene carrito activo, evitamos que rompa

        // 4. Generamos ID de orden
        String finalOrderId = (orderId == null || orderId.trim().isEmpty()) ? UUID.randomUUID().toString() : orderId;

        // 5. Creamos la orden con el comprador
        Order newOrder = new Order(finalOrderId, buyer);

        // 6. Guardamos la orden en MySQL
        return orderRepository.save(newOrder);
    }
}