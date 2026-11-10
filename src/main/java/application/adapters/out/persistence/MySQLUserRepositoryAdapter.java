package application.adapters.out.persistence;

import application.adapters.out.persistence.entities.UserEntity;
import application.adapters.out.persistence.mappers.UserPersistenceMapper;
import application.adapters.out.persistence.repositories.UserJpaRepository;
import application.domain.models.User;
import application.domain.ports.out.UserRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;


public class MySQLUserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository jpaRepository;
    private final UserPersistenceMapper mapper;

    public MySQLUserRepositoryAdapter(UserJpaRepository jpaRepository, UserPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        UserEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(String id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public boolean existsById(String id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
}