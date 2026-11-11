package application.domain.services.shoppingcart;

import application.domain.models.ShoppingCart;
import application.domain.ports.in.CreateShoppingCartUseCase;
import application.domain.ports.out.ShoppingCartRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateShoppingCartService implements CreateShoppingCartUseCase {

    private final ShoppingCartRepositoryPort repositoryPort;

    public CreateShoppingCartService(ShoppingCartRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public ShoppingCart createCart(ShoppingCart cart) {
        // Si quisieras, aquí validarías datos antes de guardar
        return repositoryPort.save(cart);
    }
}