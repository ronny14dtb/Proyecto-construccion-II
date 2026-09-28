package application.domain.ports.in;
import application.domain.models.Shipment;
public interface UpdateShipmentStatusUseCase { Shipment updateStatus(String shipmentId, String status); }