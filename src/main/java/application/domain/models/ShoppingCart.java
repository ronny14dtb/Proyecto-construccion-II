package application.domain.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private String buyerId;
    private List<CartItem> items;

    public ShoppingCart(String buyerId) {
        this.buyerId = buyerId;
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem newItem) {
        // Si ya existe el producto del mismo vendedor, actualiza cantidad; si no, lo agrega
        for (CartItem item : items) {
            if (item.getProductId().equals(newItem.getProductId()) && item.getSellerId().equals(newItem.getSellerId())) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        }
        this.items.add(newItem);
    }

    public String getBuyerId() { return buyerId; }
    public List<CartItem> getItems() { return items; }
}