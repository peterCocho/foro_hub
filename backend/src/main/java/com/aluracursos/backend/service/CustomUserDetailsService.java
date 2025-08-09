package com.aluracursos.backend.service;

import com.aluracursos.backend.domain.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service is used by Spring Security to load user-specific data during authentication.
 * It is separate from AuthService, which handles the initial login endpoint.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the user by their email address.
     * The @Transactional(readOnly = true) annotation is crucial. It ensures that the
     * lazy-loaded 'profiles' collection of the User entity is fetched within the
     * same database session, preventing a LazyInitializationException and ensuring
     * that getAuthorities() has the roles to work with.
     *
     * @param email the username (email) identifying the user whose data is required.
     * @return a fully populated UserDetails object (our User entity).
     * @throws UsernameNotFoundException if the user could not be found.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}