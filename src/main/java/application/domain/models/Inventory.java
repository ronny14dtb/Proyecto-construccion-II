package application.domain.models;

import application.domain.exceptions.InsufficientInventoryException;

public class Inventory {
    private String inventoryId;
    private Product product;
    private Warehouse warehouse;
    private int availableQuantity;
    private int reservedQuantity;

    public Inventory(String inventoryId, Product product, Warehouse warehouse, int initialQuantity) {
        if (initialQuantity < 0) {
            throw new InsufficientInventoryException("No se pueden registrar existencias iniciales negativas");
        }
        this.inventoryId = inventoryId;
        this.product = product;
        this.warehouse = warehouse;
        this.availableQuantity = initialQuantity;
        this.reservedQuantity = 0;
    }

    public void addStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad a ingresar debe ser mayor a cero");
        }
        this.availableQuantity += quantity;
    }

    public void reserveStock(int quantity) {
        if (quantity > availableQuantity) {
            throw new InsufficientInventoryException(
                "Stock insuficiente para reservar. Disponible: " + availableQuantity + ", Solicitado: " + quantity
            );
        }
        this.availableQuantity -= quantity;
        this.reservedQuantity += quantity;
    }

    public void releaseReservedStock(int quantity) {
        if (quantity > reservedQuantity) {
            throw new IllegalArgumentException("La cantidad a liberar supera la cantidad reservada actual");
        }
        this.reservedQuantity -= quantity;
        this.availableQuantity += quantity;
    }

    public void confirmSaleOut(int quantity) {
        if (quantity > reservedQuantity) {
            throw new InsufficientInventoryException("No hay suficiente inventario reservado para confirmar la salida");
        }
        this.reservedQuantity -= quantity;
    }

    public String getInventoryId() { return inventoryId; }
    public Product getProduct() { return product; }
    public Warehouse getWarehouse() { return warehouse; }
    public int getAvailableQuantity() { return availableQuantity; }
    public int getReservedQuantity() { return reservedQuantity; }
}