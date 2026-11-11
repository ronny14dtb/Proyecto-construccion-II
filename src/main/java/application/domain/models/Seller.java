package application.domain.models;

public class Seller {
    private String sellerId;
    private String storeName;
    private String email;
    private String status;

    public Seller(String sellerId, String storeName, String email, String status) {
        this.sellerId = sellerId;
        this.storeName = storeName;
        this.email = email;
        this.status = status;
    }

    public String getSellerId() { return sellerId; }
    public String getStoreName() { return storeName; }
    public String getEmail() { return email; }
    public String getStatus() { return status; }
}