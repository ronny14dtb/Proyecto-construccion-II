package application.adapters.in.web;

import application.domain.models.Order;
import application.domain.services.order.CreateOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    public static class CreateOrderRequestDto {
        private String orderId;
        private String buyerId;

        public CreateOrderRequestDto() {
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getBuyerId() {
            return buyerId;
        }

        public void setBuyerId(String buyerId) {
            this.buyerId = buyerId;
        }
    }

    private final CreateOrderService createOrderService;

    public OrderController(CreateOrderService createOrderService) {
        this.createOrderService = createOrderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequestDto request) {
        // Llamamos al servicio pasando los dos String que espera
        Order newOrder = createOrderService.createOrder(request.getOrderId(), request.getBuyerId());
        return ResponseEntity.ok(newOrder);
    }
}