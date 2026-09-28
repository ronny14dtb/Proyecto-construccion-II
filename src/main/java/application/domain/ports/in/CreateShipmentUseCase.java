package application.domain.ports.in;
import application.domain.models.Shipment;
public interface CreateShipmentUseCase { Shipment createShipment(String orderId, String address); }