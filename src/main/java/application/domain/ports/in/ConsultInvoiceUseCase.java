package application.domain.ports.in;
import application.domain.models.Invoice;
public interface ConsultInvoiceUseCase { Invoice getInvoiceById(String id); }