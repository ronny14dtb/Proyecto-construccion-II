package application.Infrastructure.security;

import application.domain.models.User;
import application.domain.ports.out.TokenProviderPort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider implements TokenProviderPort {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long validityInMilliseconds = 3600000; 

    @Override
    public String generateToken(User user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()

                .setSubject(user.getEmail().toString()) 
                .claim("role", user.getRole().name())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey)
                .compact();
    }
    

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}