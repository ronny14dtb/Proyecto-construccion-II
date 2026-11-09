package application.adapters.in.web.dto;

public class RegisterUserRequest {
    private String id;
    private String fullName;
    private String email;
    private String role;
    private String password;

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getPassword() { return password; }
}