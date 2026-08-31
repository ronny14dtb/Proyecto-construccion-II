package application.domain.models;

import application.domain.valueobjects.Address;

public class Warehouse {
    private String warehouseId;
    private String name;
    private Address location;
    private boolean isMarketplaceOwned;

    public Warehouse(String warehouseId, String name, Address location, boolean isMarketplaceOwned) {
        this.warehouseId = warehouseId;
        this.name = name;
        this.location = location;
        this.isMarketplaceOwned = isMarketplaceOwned;
    }

    public String getWarehouseId() { return warehouseId; }
    public String getName() { return name; }
    public Address getLocation() { return location; }
    public boolean isMarketplaceOwned() { return isMarketplaceOwned; }
}