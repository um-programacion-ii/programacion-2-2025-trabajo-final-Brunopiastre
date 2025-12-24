package ar.edu.um.ticketflow.backend.user.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  private String fullName;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  @Enumerated(EnumType.STRING) // <--- Agregamos esto
  private UserStatus status;   // <--- Agregamos esto

  // --- CONSTRUCTORES ---

  public User() {
  }

  public User(Long id, String email, String password, String fullName, UserRole role, UserStatus status) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.fullName = fullName;
    this.role = role;
    this.status = status;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
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

  public UserStatus getStatus() { // <--- Getter nuevo
    return status;
  }

  public void setStatus(UserStatus status) { // <--- Setter nuevo
    this.status = status;
  }
}
