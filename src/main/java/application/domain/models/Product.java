package application.domain.models;

import application.domain.enums.ProductStatus;
import application.domain.enums.ProductType;
import application.domain.valueobjects.Money;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String productId;
    private String name;
    private String description;
    private Money basePrice;
    private ProductType productType;
    private ProductStatus status;
    private Seller seller;
    private List<ProductVariant> variants;

    public Product(String productId, String name, Money basePrice, ProductType productType, Seller seller) {
        this.productId = productId;
        this.name = name;
        this.basePrice = basePrice;
        this.productType = productType;
        this.seller = seller;
        this.status = ProductStatus.PUBLICADO;
        this.variants = new ArrayList<>();
    }

    public boolean requiresInventory() {
        return this.productType == ProductType.FISICO;
    }

    public void addVariant(ProductVariant variant) {
        if (variant != null) {
            this.variants.add(variant);
        }
    }

    public void suspendProduct() {
        this.status = ProductStatus.SUSPENDIDO;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public Money getBasePrice() { return basePrice; }
    public ProductType getProductType() { return productType; }
    public ProductStatus getStatus() { return status; }
    public Seller getSeller() { return seller; }
    public List<ProductVariant> getVariants() { return variants; }
}