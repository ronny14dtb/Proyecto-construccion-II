package application.domain.ports.in;
public interface AddStockUseCase { void addStock(String productId, String warehouseId, int quantity); }