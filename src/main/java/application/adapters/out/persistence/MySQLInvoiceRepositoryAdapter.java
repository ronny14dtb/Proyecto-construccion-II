package application.adapters.out.persistence;

import application.adapters.out.persistence.entities.InvoiceEntity;
import application.adapters.out.persistence.mappers.InvoicePersistenceMapper;
import application.adapters.out.persistence.repositories.jpa.InvoiceJpaRepository;
import application.domain.models.Invoice;
import application.domain.ports.out.InvoiceRepositoryPort;

import java.util.Optional;

public class MySQLInvoiceRepositoryAdapter implements InvoiceRepositoryPort {

    private final InvoiceJpaRepository jpaRepository;
    private final InvoicePersistenceMapper mapper;

    public MySQLInvoiceRepositoryAdapter(InvoiceJpaRepository jpaRepository, InvoicePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Invoice save(Invoice invoice) {
        InvoiceEntity entity = mapper.toEntity(invoice);
        InvoiceEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Invoice> findById(String id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}