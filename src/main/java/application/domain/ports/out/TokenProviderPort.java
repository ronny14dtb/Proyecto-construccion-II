package application.domain.ports.out;

import application.domain.models.User;

public interface TokenProviderPort {
    String generateToken(User user);
}