package application.domain.models;

import application.domain.enums.BuyerStatus;
import application.domain.valueobjects.Address;
import java.util.ArrayList;
import java.util.List;

public class Buyer {
    private String buyerId;
    private User user;
    private Address primaryAddress;
    private List<Address> additionalAddresses;
    private BuyerStatus buyerStatus;

    public Buyer(String buyerId, User user, Address primaryAddress) {
        if (user == null) {
            throw new IllegalArgumentException("Un comprador debe estar asociado a un usuario válido");
        }
        if (primaryAddress == null) {
            throw new IllegalArgumentException("La dirección principal es obligatoria");
        }
        this.buyerId = buyerId;
        this.user = user;
        this.primaryAddress = primaryAddress;
        this.additionalAddresses = new ArrayList<>();
        this.buyerStatus = BuyerStatus.HABILITADO;
    }

    public void addAdditionalAddress(Address address) {
        if (address != null && !additionalAddresses.contains(address)) {
            this.additionalAddresses.add(address);
        }
    }

    public void restrictBuyer() {
        this.buyerStatus = BuyerStatus.RESTRINGIDO;
    }

    public boolean canPlaceOrder() {
        return this.buyerStatus == BuyerStatus.HABILITADO && user.getStatus() == application.domain.enums.UserStatus.ACTIVO;
    }

    public String getBuyerId() { return buyerId; }
    public User getUser() { return user; }
    public Address getPrimaryAddress() { return primaryAddress; }
    public List<Address> getAdditionalAddresses() { return additionalAddresses; }
    public BuyerStatus getBuyerStatus() { return buyerStatus; }
}