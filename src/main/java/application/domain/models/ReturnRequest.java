package application.domain.models;

public class ReturnRequest {
    private String returnId;
    private String orderId;
    private String reason;

    public ReturnRequest() {}

    public ReturnRequest(String returnId, String orderId, String reason) {
        this.returnId = returnId;
        this.orderId = orderId;
        this.reason = reason;
    }
    

    public String getReturnId() { return returnId; }
    public void setReturnId(String returnId) { this.returnId = returnId; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}