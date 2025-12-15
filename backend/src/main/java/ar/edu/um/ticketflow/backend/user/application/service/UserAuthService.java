package ar.edu.um.ticketflow.backend.user.application.service;

import ar.edu.um.ticketflow.backend.user.domain.User;
import ar.edu.um.ticketflow.backend.user.infrastructure.persistence.entity.UserEntity;
import ar.edu.um.ticketflow.backend.user.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService {

    private final UserRepository userRepository;

    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(this::toDomain);
    }

    public User ensureRegistered(String email, String fullName, String role) {
        return userRepository.findByEmail(email)
                .map(this::toDomain)
                .orElseGet(() -> {
                    UserEntity entity = new UserEntity(null, email, fullName, role == null ? "USER" : role);
                    return toDomain(userRepository.save(entity));
                });
    }

    private User toDomain(UserEntity entity) {
        return new User(entity.getId(), entity.getEmail(), entity.getFullName(), entity.getRole());
    }
}
