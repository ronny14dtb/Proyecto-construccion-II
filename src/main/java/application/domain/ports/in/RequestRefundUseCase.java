package application.domain.ports.in;
import application.domain.models.ReturnRequest;
public interface RequestRefundUseCase { ReturnRequest requestRefund(String orderId, String reason); }