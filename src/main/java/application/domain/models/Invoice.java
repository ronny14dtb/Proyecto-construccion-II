package application.domain.models;

public class Invoice {
    private String invoiceId;
    private String orderId;
    private double totalAmount;

    public Invoice() {}

    public Invoice(String invoiceId, String orderId, double totalAmount) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.totalAmount = totalAmount;
    }

    public String getInvoiceId() { return invoiceId; }
    public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}