package application.domain.models;

import application.domain.valueobjects.Money;

public class ProductVariant {
    private String variantId;
    private String name; 
    private Money price;

    public ProductVariant(String variantId, String name, Money price) {
        this.variantId = variantId;
        this.name = name;
        this.price = price;
    }

    public String getVariantId() { return variantId; }
    public String getName() { return name; }
    public Money getPrice() { return price; }
}