package com.aluracursos.backend.service;

import com.aluracursos.backend.domain.user.UserForm;
import com.aluracursos.backend.domain.user.UserMapper;
import com.aluracursos.backend.domain.user.User;
import com.aluracursos.backend.domain.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    // 🔐 Register a user with a secure password
    public User registerUser(UserForm form) {
        // Assuming 'form.contrasena()' is refactored to 'form.password()' in the UserForm record
        String hashedPassword = passwordEncoder.encode(form.password());
        User user = userMapper.mapToEntity(form, hashedPassword);
        return userRepository.save(user);
    }

}
