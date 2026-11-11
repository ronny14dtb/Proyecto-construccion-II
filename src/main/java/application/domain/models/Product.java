package application.domain.models;

public class Product {
    private String productId;
    private String sellerId; // ¡Vinculado estrictamente al vendedor!
    private String name;
    private double price;
    private int stock;

    public Product(String productId, String sellerId, String name, double price, int stock) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getProductId() { return productId; }
    public String getSellerId() { return sellerId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
}   