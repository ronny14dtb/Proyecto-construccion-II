package application.domain.services.refund;

import application.domain.models.ReturnRequest;
import application.domain.ports.in.CreateRefundUseCase;
import application.domain.ports.out.RefundRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateRefundService implements CreateRefundUseCase {

    private final RefundRepositoryPort refundRepositoryPort;

    // Inyectamos el puerto de salida para poder guardar en la base de datos
    public CreateRefundService(RefundRepositoryPort refundRepositoryPort) {
        this.refundRepositoryPort = refundRepositoryPort;
    }

    @Override
    public ReturnRequest execute(String orderId, String reason) {
        // 1. Generamos un ID único para esta devolución
        String returnId = UUID.randomUUID().toString();
        
        // 2. Instanciamos tu modelo de dominio real
        ReturnRequest newReturn = new ReturnRequest(returnId, orderId, reason);

        // 3. Guardamos el reporte (el adaptador se encargará de llevarlo a Mongo)
        return refundRepositoryPort.save(newReturn);
    }
}