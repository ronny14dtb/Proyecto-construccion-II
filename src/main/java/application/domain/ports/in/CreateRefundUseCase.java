package application.domain.ports.in;

import application.domain.models.ReturnRequest; // <-- Importamos tu clase real

public interface CreateRefundUseCase {
    ReturnRequest execute(String orderId, String reason);
}