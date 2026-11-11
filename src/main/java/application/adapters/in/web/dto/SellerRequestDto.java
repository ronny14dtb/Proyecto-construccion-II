package application.adapters.in.web.dto;

public class SellerRequestDto {
    private String storeName;
    private String email;

    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}