package application.domain.ports.in;
import application.domain.models.Order;
import application.domain.enums.OrderStatus;
public interface ChangeOrderStatusUseCase { Order changeStatus(String orderId, OrderStatus status); }