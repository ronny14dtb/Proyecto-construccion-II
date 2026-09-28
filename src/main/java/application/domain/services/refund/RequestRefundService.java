package application.domain.services.refund;

import application.domain.models.ReturnRequest;
import application.domain.ports.in.RequestRefundUseCase;
import application.domain.ports.out.RefundRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RequestRefundService implements RequestRefundUseCase {
    
    private final RefundRepositoryPort refundRepository;

    public RequestRefundService(RefundRepositoryPort refundRepository) {
        this.refundRepository = refundRepository;
    }

    @Override
    public ReturnRequest requestRefund(String orderId, String reason) {
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
        // La lógica detallada del reembolso puede implementarse luego
        return null; 
    }
}