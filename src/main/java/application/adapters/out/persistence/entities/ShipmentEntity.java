package application.adapters.out.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "shipments")
public class ShipmentEntity {

    @Id
    @Column(unique = true, nullable = false)
    private String shipmentId;

    @Column(nullable = false)
    private String orderId;

    private String carrier;
    private String trackingNumber;
    private String status;

    public ShipmentEntity() {}

    // Getters y Setters
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