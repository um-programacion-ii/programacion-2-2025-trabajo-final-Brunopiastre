package ar.edu.um.ticketflow.backend.user.infrastructure.web.controller;

import ar.edu.um.ticketflow.backend.user.application.service.UserService;
import ar.edu.um.ticketflow.backend.user.domain.User;
import ar.edu.um.ticketflow.backend.user.infrastructure.web.dto.UserDetailDto;
import ar.edu.um.ticketflow.backend.user.infrastructure.web.dto.UserRegistrationDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDetailDto register(@RequestBody UserRegistrationDto request) {
        User user = new User(
                null,
                request.getEmail(),
                request.getFullName(),
                request.getRole()
        );
        User created = userService.register(user);
        return toDto(created);
    }

    @GetMapping
    public List<UserDetailDto> getAll() {
        return userService.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private UserDetailDto toDto(User user) {
        return new UserDetailDto(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getRole()
        );
    }
}
