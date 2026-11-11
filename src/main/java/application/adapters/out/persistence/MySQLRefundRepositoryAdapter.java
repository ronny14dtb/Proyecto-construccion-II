package application.adapters.out.persistence;

import application.adapters.out.persistence.entities.RefundEntity;
import application.adapters.out.persistence.mappers.RefundPersistenceMapper;
import application.adapters.out.persistence.repositories.jpa.RefundJpaRepository;
import application.domain.models.ReturnRequest;
import application.domain.ports.out.RefundRepositoryPort;

import java.util.Optional;

public class MySQLRefundRepositoryAdapter implements RefundRepositoryPort {

    private final RefundJpaRepository jpaRepository;
    private final RefundPersistenceMapper mapper;

    public MySQLRefundRepositoryAdapter(RefundJpaRepository jpaRepository, RefundPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ReturnRequest save(ReturnRequest returnRequest) {
        RefundEntity entity = mapper.toEntity(returnRequest);
        RefundEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<ReturnRequest> findById(String id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}