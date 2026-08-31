package application.domain.models;

import application.domain.valueobjects.Money;

public class OrderItem {
    private Product product;
    private int quantity;
    private Money unitPrice;

    public OrderItem(Product product, int quantity, Money unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Money calculateSubtotal() {
        return unitPrice.multiply(quantity);
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public Money getUnitPrice() { return unitPrice; }
}