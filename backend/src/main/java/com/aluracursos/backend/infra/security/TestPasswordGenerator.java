package com.aluracursos.backend.infra.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("miContrasena123");
        System.out.println(hash);
    }
}
