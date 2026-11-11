package application.domain.services.seller;

import application.domain.models.Seller;
import application.domain.ports.out.SellerRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterSellerService {

    private final SellerRepositoryPort sellerRepositoryPort;

    public RegisterSellerService(SellerRepositoryPort sellerRepositoryPort) {
        this.sellerRepositoryPort = sellerRepositoryPort;
    }

    public Seller register(String storeName, String email) {
        String sellerId = "seller-" + UUID.randomUUID().toString().substring(0, 8);
        Seller seller = new Seller(sellerId, storeName, email, "ACTIVE");
        return sellerRepositoryPort.save(seller);
    }
}