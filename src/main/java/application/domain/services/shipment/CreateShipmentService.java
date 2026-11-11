package application.domain.services.shipment;

import application.domain.models.Order;
import application.domain.models.Shipment;
import application.domain.ports.in.CreateShipmentUseCase;
import application.domain.ports.out.OrderRepositoryPort;
import application.domain.ports.out.ShipmentRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateShipmentService implements CreateShipmentUseCase {

    private final OrderRepositoryPort orderRepository;
    private final ShipmentRepositoryPort shipmentRepository;

    public CreateShipmentService(OrderRepositoryPort orderRepository, ShipmentRepositoryPort shipmentRepository) {
        this.orderRepository = orderRepository;
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Shipment execute(String orderId, String carrier, String trackingNumber) {
        // Valida que la orden exista en MySQL antes de generar el envío
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("No se puede realizar el envío: La orden " + orderId + " no existe."));

        Shipment shipment = new Shipment(
                UUID.randomUUID().toString(),
                order.getOrderId(),
                carrier,
                trackingNumber,
                "EN_CAMINO"
        );

        return shipmentRepository.save(shipment);
    }
}