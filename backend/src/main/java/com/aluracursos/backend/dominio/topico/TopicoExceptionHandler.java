package com.aluracursos.backend.dominio.topico;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TopicoExceptionHandler {

    @ExceptionHandler(TopicoNotFoundException.class)
    public ResponseEntity<Map<String, Object>> manejarTopicoNoEncontrado(TopicoNotFoundException ex) {
        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("mensaje", ex.getMessage());
        cuerpo.put("codigo", HttpStatus.NOT_FOUND.value());
        cuerpo.put("marcaDeTiempo", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cuerpo);
    }


    // Puedes añadir aquí más @ExceptionHandler si lo necesitas
}
