package application.domain.models;

public class Shipment {
    private String shipmentId;
    private String orderId;
    private String carrier;
    private String trackingNumber;
    private String status;

    public Shipment() {}

    public Shipment(String shipmentId, String orderId, String carrier, String trackingNumber, String status) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.carrier = carrier;
        this.trackingNumber = trackingNumber;
        this.status = status;
    }

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