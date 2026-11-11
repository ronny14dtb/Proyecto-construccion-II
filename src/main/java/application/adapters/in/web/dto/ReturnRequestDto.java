package application.adapters.in.web.dto;

public class ReturnRequestDto {
    
    private String orderId;
    private String reason;

    // Constructor vacío necesario para que Spring convierta el JSON
    public ReturnRequestDto() {}

    // Getters y Setters
    public String getOrderId() { 
        return orderId; 
    }
    
    public void setOrderId(String orderId) { 
        this.orderId = orderId; 
    }

    public String getReason() { 
        return reason; 
    }
    
    public void setReason(String reason) { 
        this.reason = reason; 
    }
}