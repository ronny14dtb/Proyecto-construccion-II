package application.domain.models;

public class Inventory {
    private String inventoryId;
    private String productId;
    private String sellerId;
    private int availableQuantity;
    private String warehouseLocation;

    public Inventory(String inventoryId, String productId, String sellerId, int availableQuantity, String warehouseLocation) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.sellerId = sellerId;
        this.availableQuantity = availableQuantity;
        this.warehouseLocation = warehouseLocation;
    }

    public String getInventoryId() { return inventoryId; }
    public String getProductId() { return productId; }
    public String getSellerId() { return sellerId; }
    public int getAvailableQuantity() { return availableQuantity; }
    public String getWarehouseLocation() { return warehouseLocation; }
    
    public void updateStock(int newQuantity) {
        this.availableQuantity = newQuantity;
    }
}