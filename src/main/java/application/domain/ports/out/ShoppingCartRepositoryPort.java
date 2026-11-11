package application.domain.ports.out;

import application.domain.models.ShoppingCart;
import java.util.Optional;

public interface ShoppingCartRepositoryPort {
    ShoppingCart save(ShoppingCart cart);
    Optional<ShoppingCart> findById(String id);
}