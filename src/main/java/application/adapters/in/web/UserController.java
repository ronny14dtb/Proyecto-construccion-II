package application.adapters.in.web;

import application.adapters.in.web.dto.RegisterUserRequest;
import application.domain.enums.UserRole;
import application.domain.models.User;
import application.domain.ports.in.LoginUserUseCase;
import application.domain.ports.in.RegisterUserUseCase;
import application.domain.valueobjects.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;


    public UserController(RegisterUserUseCase registerUserUseCase, LoginUserUseCase loginUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
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


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String token = loginUserUseCase.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", e.getMessage()));
        }
    }


    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}