package application.domain.models;

import application.domain.enums.OrderStatus;
import application.domain.exceptions.InvalidOrderStateException;
import application.domain.valueobjects.Money;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private Buyer buyer;
    private List<OrderItem> items;
    private OrderStatus status;

    public Order(String orderId, Buyer buyer) {
        this.orderId = orderId;
        this.buyer = buyer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.CARRITO;
    }

    public void addItem(OrderItem item) {
        ensureNotFinalized();
        if (this.status != OrderStatus.CARRITO) {
            throw new InvalidOrderStateException("Solo se pueden modificar ítems en estado CARRITO");
        }
        this.items.add(item);
    }

    public void changeStatus(OrderStatus newStatus) {
        ensureNotFinalized();
        this.status = newStatus;
    }

    private void ensureNotFinalized() {
        if (this.status == OrderStatus.FINALIZADO) {
            throw new InvalidOrderStateException("Un pedido FINALIZADO no se puede modificar bajo ninguna circunstancia");
        }
    }

    public Money calculateTotal() {
        Money total = Money.cop(BigDecimal.ZERO);
        for (OrderItem item : items) {
            total = total.add(item.calculateSubtotal());
        }
        return total;
    }

    public String getOrderId() { return orderId; }
    public Buyer getBuyer() { return buyer; }
    public List<OrderItem> getItems() { return items; }
    public OrderStatus getStatus() { return status; }
}