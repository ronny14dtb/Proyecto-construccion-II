package application.domain.ports.out;

import application.domain.models.Buyer;
import java.util.Optional;

public interface BuyerRepositoryPort {
    Buyer save(Buyer buyer);
    Optional<Buyer> findById(Long id);
    Optional<Buyer> findByBuyerId(String buyerId); // <-- Agrega este método
}