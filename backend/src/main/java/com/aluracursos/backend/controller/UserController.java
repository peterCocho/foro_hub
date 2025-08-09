package com.aluracursos.backend.controller;

import com.aluracursos.backend.domain.user.*;
import com.aluracursos.backend.dto.LoginForm;
import com.aluracursos.backend.service.AuthService;
import com.aluracursos.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    // 📌 User Registration
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriBuilder) {
        User newUser = userService.registerUser(userForm);

        // Build the URI for the newly created resource
        URI uri = uriBuilder.path("/users/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        // Return 201 Created with the location and safe user data (UserDTO)
        return ResponseEntity.created(uri).body(new UserDTO(newUser));
    }

    // 🔐 User Authentication
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody @Valid LoginForm loginForm) {
        AuthResponse response = authService.authenticate(loginForm);
        return ResponseEntity.ok(response);
    }

    // 👤 Get current user's profile
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUserProfile(Authentication authentication) {
        // Spring Security provides the authenticated user's principal.
        User currentUser = (User) authentication.getPrincipal();

        // Return a safe DTO (UserDTO) to avoid exposing sensitive data.
        // Never return a UserForm or the User entity directly from an endpoint.
        return ResponseEntity.ok(new UserDTO(currentUser));
    }

    // For development purposes only
    @GetMapping("/devtools-test")
    public String devtoolsTest() {
        return "Updated: " + System.currentTimeMillis();
    }

}