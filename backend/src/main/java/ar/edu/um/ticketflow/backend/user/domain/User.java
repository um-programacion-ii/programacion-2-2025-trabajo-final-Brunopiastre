package ar.edu.um.ticketflow.backend.user.domain.user;

import ar.edu.um.ticketflow.backend.user.domain.UserRole;
import jakarta.persistence.*;

@Entity // <--- ESTO convierte la clase en tabla
@Table(name = "users") // La tabla se llamará 'users' en MySQL
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false) // <--- Agregamos esto obligatorio
  private String password;

  private String fullName;

  @Enumerated(EnumType.STRING) // Guardará "ADMIN" o "USER" como texto
  private UserRole role;       // Usamos tu Enum 'UserRole' en vez de String suelto

  // --- CONSTRUCTORES ---

  public User() {
  }

  public User(Long id, String email, String password, String fullName, UserRole role) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.fullName = fullName;
    this.role = role;
  }

  // --- GETTERS Y SETTERS ---

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() { // <--- Nuevo Getter
    return password;
  }

  public void setPassword(String password) { // <--- Nuevo Setter
    this.password = password;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }
}
