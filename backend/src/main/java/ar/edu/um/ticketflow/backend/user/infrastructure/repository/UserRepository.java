package ar.edu.um.ticketflow.backend.user.infrastructure.repository;

import ar.edu.um.ticketflow.backend.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
