package application.adapters.in.web;

import application.adapters.in.web.dto.RegisterUserRequest;
import application.domain.enums.UserRole;
import application.domain.models.User;
import application.domain.ports.in.RegisterUserUseCase;
import application.domain.valueobjects.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest request) {
        try {
            User user = new User(
                    request.getId(),
                    request.getFullName(),
                    new Email(request.getEmail()),
                    UserRole.valueOf(request.getRole().toUpperCase()),
                    request.getPassword()
            );
            
            registerUserUseCase.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}