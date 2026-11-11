package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.UserEntity;
import application.domain.enums.UserRole;
import application.domain.models.User;
import application.domain.valueobjects.Email;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {

    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getFullName(),
                user.getEmail().getValue(), 
                user.getRole().name(),
                user.getPassword(),
                user.getStatus().name()
        );
    }

    public User toDomain(UserEntity entity) {
        User user = new User(
                entity.getId(),
                entity.getFullName(),
                new Email(entity.getEmail()), 
                UserRole.valueOf(entity.getRole()),
                entity.getPassword()
        );
        
        if ("BLOQUEADO".equals(entity.getStatus())) {
            user.blockUser();
        }
        return user;
    }
}