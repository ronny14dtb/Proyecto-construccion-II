package application.domain.services.seller;

import application.domain.models.Seller;
import application.domain.ports.in.RegisterSellerUseCase;
import application.domain.ports.out.SellerRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RegisterSellerService implements RegisterSellerUseCase {
    
    private final SellerRepositoryPort sellerRepository;

    public RegisterSellerService(SellerRepositoryPort sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller registerSeller(Seller seller) {
        if (seller == null) {
            throw new IllegalArgumentException("Seller to register cannot be null");
        }
        return sellerRepository.save(seller);
    }
}