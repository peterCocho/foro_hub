package com.aluracursos.backend.infra;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenProvider {

    private final String secretKey = "claveSuperSegura1234567890ABCDEF123456";
    private final long expirationMs = 86400000; // 24 hours

    public String generarToken(String emailUsuario) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(emailUsuario)
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String obtenerEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
