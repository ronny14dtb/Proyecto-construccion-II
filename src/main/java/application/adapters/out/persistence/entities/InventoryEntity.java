package application.adapters.out.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "inventories")
public class InventoryEntity {

    @Id
    private String inventoryId;

    @Column(nullable = false, unique = true)
    private String productId;

    @Column(nullable = false)
    private String sellerId;

    @Column(nullable = false)
    private int availableQuantity;

    // Solución desde el código: valor por defecto en Java y definición SQL
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int reservedQuantity = 0;

    private String warehouseLocation;

    public InventoryEntity() {}

    public InventoryEntity(String inventoryId, String productId, String sellerId, int availableQuantity, int reservedQuantity, String warehouseLocation) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.sellerId = sellerId;
        this.availableQuantity = availableQuantity;
        this.reservedQuantity = reservedQuantity;
        this.warehouseLocation = warehouseLocation;
    }

    public String getInventoryId() { return inventoryId; }
    public void setInventoryId(String inventoryId) { this.inventoryId = inventoryId; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getSellerId() { return sellerId; }
    public void setSellerId(String sellerId) { this.sellerId = sellerId; }

    public int getAvailableQuantity() { return availableQuantity; }
    public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }

    public int getReservedQuantity() { return reservedQuantity; }
    public void setReservedQuantity(int reservedQuantity) { this.reservedQuantity = reservedQuantity; }

    public String getWarehouseLocation() { return warehouseLocation; }
    public void setWarehouseLocation(String warehouseLocation) { this.warehouseLocation = warehouseLocation; }
}