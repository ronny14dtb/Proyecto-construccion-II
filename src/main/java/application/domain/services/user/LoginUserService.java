package application.domain.services.user;

import application.domain.models.User;
import application.domain.ports.in.LoginUserUseCase; 
import application.domain.ports.out.UserRepositoryPort;
import application.domain.ports.out.PasswordEncoderPort;
import application.domain.ports.out.TokenProviderPort;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService implements LoginUserUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final TokenProviderPort tokenProvider;

    public LoginUserService(UserRepositoryPort userRepository, 
                            PasswordEncoderPort passwordEncoder, 
                            TokenProviderPort tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

   @Override
    public String login(String email, String rawPassword) {
        System.out.println("=== INTENTO DE LOGIN ===");
        System.out.println("Buscando email exacto: [" + email + "]");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("¡ERROR: El repositorio no encontró ningún usuario con el correo: [" + email + "]!");
                    return new IllegalArgumentException("Credenciales inválidas");
                });

        System.out.println("¡Usuario encontrado en la BD!: " + user.getEmail());

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            System.out.println("¡ERROR: La contraseña no coincide con el hash guardado!");
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        if (user.getStatus().name().equals("BLOQUEADO")) {
            throw new IllegalStateException("El usuario se encuentra bloqueado");
        }

        return tokenProvider.generateToken(user);
    }
}