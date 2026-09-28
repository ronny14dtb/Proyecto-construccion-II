package application.domain.ports.in;

public interface ReserveStockUseCase { 
    void reserveStock(String productId, String warehouseId, int quantity); 
}