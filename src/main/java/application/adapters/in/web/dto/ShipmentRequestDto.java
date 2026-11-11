package application.adapters.in.web.dto;

public class ShipmentRequestDto {
    private String orderId;
    private String carrier;
    private String trackingNumber;

    public ShipmentRequestDto() {}

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getCarrier() { return carrier; }
    public void setCarrier(String carrier) { this.carrier = carrier; }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
}