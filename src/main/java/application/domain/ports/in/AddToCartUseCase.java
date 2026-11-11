package application.domain.ports.in;

import application.domain.models.ShoppingCart;

public interface AddToCartUseCase {
    ShoppingCart addProduct(String buyerId, String productId, String sellerId, int quantity);
}