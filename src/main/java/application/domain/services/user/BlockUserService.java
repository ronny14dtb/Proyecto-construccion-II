package application.domain.services.user;

import application.domain.models.User;
import application.domain.ports.in.BlockUserUseCase;
import application.domain.ports.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class BlockUserService implements BlockUserUseCase {
    
    private final UserRepositoryPort userRepository;

    public BlockUserService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void blockUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        user.blockUser(); 
        userRepository.save(user);
    }
}