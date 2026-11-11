package application.domain.services.refund;

import application.domain.models.Order;
import application.domain.models.ReturnRequest;
import application.domain.ports.in.CreateRefundUseCase;
import application.domain.ports.out.OrderRepositoryPort;
import application.domain.ports.out.RefundRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateRefundService implements CreateRefundUseCase {

    private final OrderRepositoryPort orderRepository;
    private final RefundRepositoryPort refundRepository;

    public CreateRefundService(OrderRepositoryPort orderRepository, RefundRepositoryPort refundRepository) {
        this.orderRepository = orderRepository;
        this.refundRepository = refundRepository;
    }

    @Override
    public ReturnRequest execute(String orderId, String reason) {
        // 1. Validamos que la orden exista en la base de datos (MySQL)
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("No se puede procesar la devolución: La orden " + orderId + " no existe."));

        // 2. Creamos la solicitud de devolución usando el constructor exacto
        ReturnRequest returnRequest = new ReturnRequest(
                UUID.randomUUID().toString(),
                order.getOrderId(),
                reason
        );

        // 3. Guardamos la devolución (en MongoDB o donde corresponda) y la retornamos
        return refundRepository.save(returnRequest);
    }
}