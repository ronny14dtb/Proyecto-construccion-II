package application.adapters.in.web.dto;

public class CreateOrderRequestDto {
    private String orderId;
    private String buyerId;

    // Getters y Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    
    public String getBuyerId() { return buyerId; }
    public void setBuyerId(String buyerId) { this.buyerId = buyerId; }
}