package application.domain.services.shoppingcart;

import application.domain.models.ShoppingCart;
import application.domain.ports.in.CreateShoppingCartUseCase;
import application.domain.ports.out.ShoppingCartRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateShoppingCartService implements CreateShoppingCartUseCase {

    private final ShoppingCartRepositoryPort shoppingCartRepositoryPort;

    public CreateShoppingCartService(ShoppingCartRepositoryPort shoppingCartRepositoryPort) {
        this.shoppingCartRepositoryPort = shoppingCartRepositoryPort;
    }

    @Override
    public ShoppingCart createCart(ShoppingCart shoppingCart) {
        return shoppingCartRepositoryPort.save(shoppingCart);
    }
}