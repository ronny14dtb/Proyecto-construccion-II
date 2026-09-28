package application.domain.ports.in;
import application.domain.models.Product;
public interface RegisterProductUseCase { Product registerProduct(Product product); }