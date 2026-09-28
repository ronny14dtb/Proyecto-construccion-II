package application.domain.ports.in;
import application.domain.models.Order;
public interface CreateOrderUseCase { Order createOrder(String orderId, String buyerId); }