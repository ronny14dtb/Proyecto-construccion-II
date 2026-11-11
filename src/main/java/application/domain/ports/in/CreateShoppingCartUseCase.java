package application.domain.ports.in;

import application.domain.models.ShoppingCart;

public interface CreateShoppingCartUseCase {
    ShoppingCart createCart(ShoppingCart cart);
}