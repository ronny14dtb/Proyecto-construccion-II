package application.domain.ports.in;
import application.domain.models.Order;
import application.domain.models.OrderItem;
public interface AddItemToOrderUseCase { Order addItem(String orderId, OrderItem item); }