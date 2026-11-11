package application.domain.services.user;

import application.domain.models.User;
import application.domain.ports.in.RegisterUserUseCase;
import application.domain.ports.out.PasswordEncoderPort;
import application.domain.ports.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService implements RegisterUserUseCase {
    
    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoder; 


    public RegisterUserService(UserRepositoryPort userRepository, PasswordEncoderPort passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("El usuario a registrar no puede ser nulo");
        }
        if (userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("Ya existe una cuenta registrada con el ID/Documento: " + user.getId());
        }

        String emailString = user.getEmail().toString(); 
        if (userRepository.existsByEmail(emailString)) {
            throw new IllegalArgumentException("El correo electrónico ya se encuentra registrado: " + emailString);
        }
        

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }
}