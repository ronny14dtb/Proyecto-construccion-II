package application.adapters.out.persistence;

import application.adapters.out.persistence.entities.RefundEntity;
import application.adapters.out.persistence.mappers.RefundPersistenceMapper;
import application.adapters.out.persistence.repositories.jpa.RefundJpaRepository; // Tu repo existente
import application.domain.models.ReturnRequest;
import application.domain.ports.out.RefundRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MySQLRefundRepositoryAdapter implements RefundRepositoryPort {

    private final RefundJpaRepository jpaRepository;
    private final RefundPersistenceMapper mapper;

    public MySQLRefundRepositoryAdapter(RefundJpaRepository jpaRepository, RefundPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ReturnRequest save(ReturnRequest returnRequest) {
        // 1. Traducimos el dominio a entidad
        RefundEntity entity = mapper.toEntity(returnRequest);
        // 2. Guardamos en MySQL
        RefundEntity savedEntity = jpaRepository.save(entity);
        // 3. Traducimos la entidad guardada de vuelta al dominio
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<ReturnRequest> findById(String returnId) {
        // Si tu ID en MySQL es Long, haz: Long idNum = Long.valueOf(returnId);
        // y pásale idNum al findById. Si es String, pásalo directo:
        return jpaRepository.findById(returnId).map(mapper::toDomain);
    }
}