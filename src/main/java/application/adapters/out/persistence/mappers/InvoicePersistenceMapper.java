package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.InvoiceEntity;
import application.domain.models.Invoice;
import org.springframework.stereotype.Component;

@Component
public class InvoicePersistenceMapper {

    public InvoiceEntity toEntity(Invoice invoice) {
        if (invoice == null) return null;

        InvoiceEntity entity = new InvoiceEntity();
        
        // CAMBIA .getInvoiceId() por el método real que tenga tu clase Invoice, por ejemplo:
        // entity.setInvoiceId(invoice.getId()); 
        entity.setInvoiceId(invoice.getInvoiceId()); 
        
        return entity;
    }

    public Invoice toDomain(InvoiceEntity entity) {
        if (entity == null) return null;

        Invoice invoice = new Invoice(); 
        return invoice;
    }
}