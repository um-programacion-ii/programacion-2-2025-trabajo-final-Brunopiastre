package ar.edu.um.ticketflow.backend.user.infrastructure.repository;

import ar.edu.um.ticketflow.backend.user.domain.User;
import ar.edu.um.ticketflow.backend.user.domain.UserRole;
import ar.edu.um.ticketflow.backend.user.domain.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void shouldSaveAndFindUser() {
    // Crear un usuario usando la nueva estructura (con Enums)
    User newUser = new User(
      null,
      "test@example.com",
      "password123",
      "Test User",
      UserRole.ROLE_USER,
      UserStatus.ACTIVE
    );

    // Guardar
    User savedUser = userRepository.save(newUser);

    // Verificar
    assertThat(savedUser.getId()).isNotNull();

    // Buscar
    Optional<User> foundUser = userRepository.findById(savedUser.getId());
    assertThat(foundUser).isPresent();
    assertThat(foundUser.get().getEmail()).isEqualTo("test@example.com");
  }
}
