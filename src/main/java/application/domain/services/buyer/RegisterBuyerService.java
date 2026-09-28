package application.domain.services.buyer;

import application.domain.models.Buyer;
import application.domain.ports.in.RegisterBuyerUseCase;
import application.domain.ports.out.BuyerRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RegisterBuyerService implements RegisterBuyerUseCase {
    
    private final BuyerRepositoryPort buyerRepository;

    public RegisterBuyerService(BuyerRepositoryPort buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    @Override
    public Buyer registerBuyer(Buyer buyer) {
        if (buyer == null) {
            throw new IllegalArgumentException("Buyer to register cannot be null");
        }
        return buyerRepository.save(buyer);
    }
}