package application.domain.ports.in;
import application.domain.models.Invoice;
public interface GenerateInvoiceUseCase { Invoice generateInvoice(String orderId); }