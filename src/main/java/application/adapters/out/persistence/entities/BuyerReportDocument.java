package application.adapters.out.persistence.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "buyer_reports")
public class BuyerReportDocument {

    @Id
    private String id;
    private String buyerId;
    
    private List<CartItemSummary> cartItems;
    private List<OrderSummary> orders;
    private List<ShipmentSummary> shipments;
    private List<ReturnSummary> returns;
    
    private String updatedAt;

    public BuyerReportDocument() {}

    // --- Sub-clases fuertemente tipadas ---
    public static class CartItemSummary {
        private String productId;
        private String sellerId;
        private int quantity;

        public CartItemSummary() {}
        public CartItemSummary(String productId, String sellerId, int quantity) {
            this.productId = productId;
            this.sellerId = sellerId;
            this.quantity = quantity;
        }

        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }
        public String getSellerId() { return sellerId; }
        public void setSellerId(String sellerId) { this.sellerId = sellerId; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }

    public static class OrderSummary {
        private String orderId;
        private String buyerId;
        private String status;

        public OrderSummary() {}
        public String getOrderId() { return orderId; }
        public void setOrderId(String orderId) { this.orderId = orderId; }
        public String getBuyerId() { return buyerId; }
        public void setBuyerId(String buyerId) { this.buyerId = buyerId; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    public static class ShipmentSummary {
        private String shipmentId;
        private String orderId;
        private String carrier;
        private String trackingNumber;
        private String status;

        public ShipmentSummary() {}
        public String getShipmentId() { return shipmentId; }
        public void setShipmentId(String shipmentId) { this.shipmentId = shipmentId; }
        public String getOrderId() { return orderId; }
        public void setOrderId(String orderId) { this.orderId = orderId; }
        public String getCarrier() { return carrier; }
        public void setCarrier(String carrier) { this.carrier = carrier; }
        public String getTrackingNumber() { return trackingNumber; }
        public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    public static class ReturnSummary {
        private String returnId;
        private String orderId;
        private String reason;

        public ReturnSummary() {}
        public String getReturnId() { return returnId; }
        public void setReturnId(String returnId) { this.returnId = returnId; }
        public String getOrderId() { return orderId; }
        public void setOrderId(String orderId) { this.orderId = orderId; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
    }

    // --- Getters y Setters principales ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getBuyerId() { return buyerId; }
    public void setBuyerId(String buyerId) { this.buyerId = buyerId; }

    public List<CartItemSummary> getCartItems() { return cartItems; }
    public void setCartItems(List<CartItemSummary> cartItems) { this.cartItems = cartItems; }

    public List<OrderSummary> getOrders() { return orders; }
    public void setOrders(List<OrderSummary> orders) { this.orders = orders; }

    public List<ShipmentSummary> getShipments() { return shipments; }
    public void setShipments(List<ShipmentSummary> shipments) { this.shipments = shipments; }

    public List<ReturnSummary> getReturns() { return returns; }
    public void setReturns(List<ReturnSummary> returns) { this.returns = returns; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}