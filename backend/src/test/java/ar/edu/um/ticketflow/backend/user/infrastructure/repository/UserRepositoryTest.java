package ar.edu.um.ticketflow.backend.user.infrastructure.repository;

import ar.edu.um.ticketflow.backend.user.infrastructure.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void savesAndFindsByEmail() {
        UserEntity e = new UserEntity(null, "test@example.com", "Test User", "USER");
        userRepository.save(e);

        Optional<UserEntity> found = userRepository.findByEmail("test@example.com");
        assertThat(found).isPresent();
        assertThat(found.get().getFullName()).isEqualTo("Test User");
    }
}
