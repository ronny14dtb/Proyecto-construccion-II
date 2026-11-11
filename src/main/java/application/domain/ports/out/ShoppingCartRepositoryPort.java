package application.domain.ports.out;

import application.domain.models.ShoppingCart;
import java.util.Optional;

public interface ShoppingCartRepositoryPort {
    ShoppingCart save(ShoppingCart shoppingCart); // <-- Asegúrate de tener este método aquí
    Optional<ShoppingCart> findById(String buyerId);
}