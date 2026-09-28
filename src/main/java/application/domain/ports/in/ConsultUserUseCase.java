package application.domain.ports.in;
import application.domain.models.User;
public interface ConsultUserUseCase { User getUserById(String userId); }