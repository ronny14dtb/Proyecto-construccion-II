package application.domain.models;

import application.domain.enums.UserRole;
import application.domain.enums.UserStatus;
import application.domain.valueobjects.Email;

public class User {
    private String id;
    private String fullName;
    private Email email;
    private UserRole role;
    private UserStatus status;
    private String password; 

    public User(String id, String fullName, Email email, UserRole role, String password) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El identificador de usuario es obligatorio");
        }
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo es obligatorio");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        this.id = id.trim();
        this.fullName = fullName.trim();
        this.email = email;
        this.role = role;
        this.password = password; 
        this.status = UserStatus.ACTIVO;
    }

    public void blockUser() {
        this.status = UserStatus.BLOQUEADO;
    }

    public void activateUser() {
        this.status = UserStatus.ACTIVO;
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public Email getEmail() { return email; }
    public UserRole getRole() { return role; }
    public UserStatus getStatus() { return status; }
    public String getPassword() { return password; }
}