package application.domain.services.invoice;

import application.domain.models.Invoice;
import application.domain.ports.in.ConsultInvoiceUseCase;
import application.domain.ports.out.InvoiceRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ConsultInvoiceService implements ConsultInvoiceUseCase {
    private final InvoiceRepositoryPort invoiceRepository;

    public ConsultInvoiceService(InvoiceRepositoryPort invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice getInvoiceById(String id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found with id: " + id));
    }
}