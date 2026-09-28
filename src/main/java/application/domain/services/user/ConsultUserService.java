package application.domain.services.user;

import application.domain.models.User;
import application.domain.ports.in.ConsultUserUseCase;
import application.domain.ports.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ConsultUserService implements ConsultUserUseCase {
    
    private final UserRepositoryPort userRepository;

    public ConsultUserService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
    }
}