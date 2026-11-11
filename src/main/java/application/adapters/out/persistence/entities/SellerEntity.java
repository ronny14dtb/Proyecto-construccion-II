package application.adapters.out.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sellers")
public class SellerEntity {

    @Id
    private String sellerId;
    
    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false, unique = true)
    private String email;

    private String status;

    public SellerEntity() {}

    public SellerEntity(String sellerId, String storeName, String email, String status) {
        this.sellerId = sellerId;
        this.storeName = storeName;
        this.email = email;
        this.status = status;
    }

    public String getSellerId() { return sellerId; }
    public void setSellerId(String sellerId) { this.sellerId = sellerId; }

    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}