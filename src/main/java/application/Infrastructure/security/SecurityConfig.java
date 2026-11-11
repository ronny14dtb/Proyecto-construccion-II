package application.Infrastructure.security;

import application.domain.ports.out.PasswordEncoderPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

  @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable()) 
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/**", "/api/users/register", "/api/users/login", "/api/orders/**").permitAll()
            .requestMatchers("/api/shipments", "/api/shipments/**").permitAll() 
            .requestMatchers("/api/returns", "/api/returns/**").permitAll()
            .requestMatchers("/api/reports/**").permitAll()
            .requestMatchers("/api/sellers/**").permitAll()
            .requestMatchers("/api/products/**").permitAll()
            .requestMatchers("/api/inventory/**").permitAll()
            .requestMatchers("/api/carts/**", "/api/carts").permitAll() // <--- ¡Agrégalo aquí!

            .anyRequest().authenticated() 
        )
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}


    @Bean
    public PasswordEncoderPort passwordEncoderPort() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return new PasswordEncoderPort() {
            @Override
            public String encode(String rawPassword) {
                return encoder.encode(rawPassword);
            }

            @Override
            public boolean matches(String rawPassword, String encodedPassword) {
                return encoder.matches(rawPassword, encodedPassword); 
            }
        };
    }
}