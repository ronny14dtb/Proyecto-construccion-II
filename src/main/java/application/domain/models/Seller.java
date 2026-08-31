package application.domain.models;

public class Seller {
    private String sellerId;
    private User user;
    private String storeName;

    public Seller(String sellerId, User user, String storeName) {
        if (user == null || storeName == null || storeName.trim().isEmpty()) {
            throw new IllegalArgumentException("Los datos del vendedor son inválidos");
        }
        this.sellerId = sellerId;
        this.user = user;
        this.storeName = storeName.trim();
    }

    public String getSellerId() { return sellerId; }
    public User getUser() { return user; }
    public String getStoreName() { return storeName; }
}