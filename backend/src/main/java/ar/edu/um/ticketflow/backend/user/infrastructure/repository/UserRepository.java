package ar.edu.um.ticketflow.backend.user.infrastructure.repository;

import ar.edu.um.ticketflow.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // Spring Data JPA crea la consulta automáticamente por el nombre del método
  Optional<User> findByEmail(String email);

}
