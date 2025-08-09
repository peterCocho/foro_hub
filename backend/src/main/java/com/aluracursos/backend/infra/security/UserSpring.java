package com.aluracursos.backend.infra.security;

import com.aluracursos.backend.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserSpring implements UserDetails {
    private final String correoElectronico;
    private final String contrasena;
    private final List<GrantedAuthority> authorities;

    public UserSpring(User user) {
        this.correoElectronico = user.getUsername();
        this.contrasena = user.getPassword();
        this.authorities = user.getAuthorities().stream().collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

