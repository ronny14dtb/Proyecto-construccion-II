package application.domain.ports.in;

public interface ReleaseStockUseCase { 
    void releaseStock(String productId, String warehouseId, int quantity); 
}