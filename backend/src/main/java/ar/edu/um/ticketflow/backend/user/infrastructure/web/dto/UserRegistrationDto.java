package ar.edu.um.ticketflow.backend.user.infrastructure.web.dto;

public class UserRegistrationDto {
  private String email;
  private String password; // <--- AGREGAR ESTO
  private String fullName;
  private String role;

  // Genera constructores, getters y setters igual que en los otros archivos
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }

  public String getFullName() { return fullName; }
  public void setFullName(String fullName) { this.fullName = fullName; }

  public String getRole() { return role; }
  public void setRole(String role) { this.role = role; }
}
