package com.aluracursos.backend.dominio.topico;


public class TopicoNotFoundException extends RuntimeException {
    public TopicoNotFoundException(Long id) {
        super("No se encontró ningún tópico con el ID " + id);
    }
}

