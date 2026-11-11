package application.adapters.out.persistence.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "shopping_carts")
public class ShoppingCartDocument {
    @Id
    private String buyerId;
    private List<CartItemDocument> items;

    public ShoppingCartDocument() {}

    public ShoppingCartDocument(String buyerId, List<CartItemDocument> items) {
        this.buyerId = buyerId;
        this.items = items;
    }

    public String getBuyerId() { return buyerId; }
    public void setBuyerId(String buyerId) { this.buyerId = buyerId; }
    public List<CartItemDocument> getItems() { return items; }
    public void setItems(List<CartItemDocument> items) { this.items = items; }

    public static class CartItemDocument {
        private String productId;
        private String sellerId;
        private int quantity;

        public CartItemDocument() {}

        public CartItemDocument(String productId, String sellerId, int quantity) {
            this.productId = productId;
            this.sellerId = sellerId;
            this.quantity = quantity;
        }

        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }
        public String getSellerId() { return sellerId; }
        public void setSellerId(String sellerId) { this.sellerId = sellerId; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}