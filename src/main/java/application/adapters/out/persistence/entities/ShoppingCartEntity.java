package application.adapters.out.persistence.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shopping_carts")
public class ShoppingCartEntity {

    @Id
    private String cartId;
    private String buyerId;
    private Double total;

    public ShoppingCartEntity() {}

    public String getCartId() { return cartId; }
    public void setCartId(String cartId) { this.cartId = cartId; }

    public String getBuyerId() { return buyerId; }
    public void setBuyerId(String buyerId) { this.buyerId = buyerId; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
}