package ar.edu.um.ticketflow.backend.user.application.service;

import ar.edu.um.ticketflow.backend.user.domain.User;
import ar.edu.um.ticketflow.backend.user.infrastructure.persistence.entity.UserEntity;
import ar.edu.um.ticketflow.backend.user.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        UserEntity entity = toEntity(user);
        UserEntity saved = userRepository.save(entity);
        return toDomain(saved);
    }

    public List<User> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getRole()
        );
    }

    private User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getEmail(),
                entity.getFullName(),
                entity.getRole()
        );
    }
}
