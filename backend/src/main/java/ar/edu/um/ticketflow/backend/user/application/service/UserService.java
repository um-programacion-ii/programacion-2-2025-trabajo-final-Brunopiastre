package ar.edu.um.ticketflow.backend.user.application.service;

import ar.edu.um.ticketflow.backend.user.domain.User;
import ar.edu.um.ticketflow.backend.user.infrastructure.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder; // <--- IMPORT NUEVO
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder; // <--- CAMPO NUEVO

  // Inyectamos el PasswordEncoder en el constructor
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User createUser(User user) {
    // ENCRIPTAMOS LA CONTRASEÑA ANTES DE GUARDAR
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  // ... resto de métodos ...
}
