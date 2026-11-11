package application.domain.services.shoppingcart;

import application.domain.models.CartItem;
import application.domain.models.ShoppingCart;
import application.domain.ports.in.AddToCartUseCase;
import application.domain.ports.out.ShoppingCartRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class AddToCartService implements AddToCartUseCase {

    private final ShoppingCartRepositoryPort shoppingCartRepository;

    public AddToCartService(ShoppingCartRepositoryPort shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ShoppingCart addProduct(String buyerId, String productId, String sellerId, int quantity) {
        // Busca si el comprador ya tiene un carrito en MongoDB, o crea uno nuevo
        ShoppingCart cart = shoppingCartRepository.findById(buyerId)
                .orElse(new ShoppingCart(buyerId));

        // Agrega el ítem aplicando la lógica de negocio del modelo
        CartItem newItem = new CartItem(productId, sellerId, quantity);
        cart.addItem(newItem);

        // Guarda y retorna el carrito actualizado
        return shoppingCartRepository.save(cart);
    }
}