package application.domain.ports.in;

import application.domain.models.Shipment;

public interface CreateShipmentUseCase {
    Shipment execute(String orderId, String carrier, String trackingNumber);
}