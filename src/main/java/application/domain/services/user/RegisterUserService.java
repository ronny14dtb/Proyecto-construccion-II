package application.domain.services.user;

import application.domain.models.User;
import application.domain.ports.in.RegisterUserUseCase;
import application.domain.ports.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService implements RegisterUserUseCase {
    
    private final UserRepositoryPort userRepository;

    public RegisterUserService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return userRepository.save(user);
    }
}