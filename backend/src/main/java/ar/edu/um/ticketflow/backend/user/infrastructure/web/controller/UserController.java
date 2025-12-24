package ar.edu.um.ticketflow.backend.user.infrastructure.web.controller;

import ar.edu.um.ticketflow.backend.user.application.service.UserService;
import ar.edu.um.ticketflow.backend.user.domain.User;
import ar.edu.um.ticketflow.backend.user.domain.UserRole;
import ar.edu.um.ticketflow.backend.user.domain.UserStatus;
import ar.edu.um.ticketflow.backend.user.infrastructure.web.dto.UserDetailDto;
import ar.edu.um.ticketflow.backend.user.infrastructure.web.dto.UserRegistrationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users") // Prefijo global
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  // --- MODIFICACIÓN AQUÍ ---
  // Antes: @PostMapping
  // Ahora: @PostMapping("/register")
  // URL Final: POST http://localhost:8080/api/users/register
  @PostMapping("/register")
  public ResponseEntity<UserDetailDto> register(@RequestBody UserRegistrationDto registrationDto) {

    User newUser = new User(
      null,
      registrationDto.getEmail(),
      registrationDto.getPassword(),
      registrationDto.getFullName(),
      UserRole.ROLE_USER,
      UserStatus.ACTIVE
    );

    User createdUser = userService.createUser(newUser);

    UserDetailDto detailDto = new UserDetailDto(
      createdUser.getId(),
      createdUser.getEmail(),
      createdUser.getFullName(),
      createdUser.getRole().name()
    );

    return new ResponseEntity<>(detailDto, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDetailDto> getUser(@PathVariable("id") Long id) {
    Optional<User> userOptional = userService.getUserById(id);

    if (userOptional.isPresent()) {
      User user = userOptional.get();
      UserDetailDto dto = new UserDetailDto(
        user.getId(),
        user.getEmail(),
        user.getFullName(),
        user.getRole().name()
      );
      return ResponseEntity.ok(dto);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
