package application.domain.models;

public class CartItem {
    private String productId;
    private String sellerId;
    private int quantity;

    public CartItem(String productId, String sellerId, int quantity) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.quantity = quantity;
    }

    public String getProductId() { return productId; }
    public String getSellerId() { return sellerId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}