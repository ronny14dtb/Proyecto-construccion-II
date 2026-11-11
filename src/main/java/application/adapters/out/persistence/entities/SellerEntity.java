package application.adapters.out.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sellers")
public class SellerEntity {

    @Id
    private String sellerId;
    private String storeName;
    private String status;

    public SellerEntity() {}

    public String getSellerId() { return sellerId; }
    public void setSellerId(String sellerId) { this.sellerId = sellerId; }

    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}