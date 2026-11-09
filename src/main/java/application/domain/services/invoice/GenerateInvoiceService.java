package application.domain.services.invoice;

import application.domain.models.Invoice;
import application.domain.ports.in.GenerateInvoiceUseCase;
import application.domain.ports.out.InvoiceRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GenerateInvoiceService implements GenerateInvoiceUseCase {
    private final InvoiceRepositoryPort invoiceRepository;

    public GenerateInvoiceService(InvoiceRepositoryPort invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice generateInvoice(String orderId) {
        // Lógica de generación pendiente
        return null;
    }
}