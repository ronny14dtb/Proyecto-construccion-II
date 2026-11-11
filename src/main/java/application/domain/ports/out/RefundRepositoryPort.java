package application.domain.ports.out;

import application.domain.models.ReturnRequest;
import java.util.Optional;

public interface RefundRepositoryPort {
    ReturnRequest save(ReturnRequest returnRequest);
    Optional<ReturnRequest> findById(String returnId);
}