package application.domain.ports.in;

public interface LoginUserUseCase {
    String login(String email, String password);
}