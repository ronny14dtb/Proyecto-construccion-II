package application.domain.ports.in;
import application.domain.models.Product;
public interface ConsultProductUseCase { Product getProductById(String id); }