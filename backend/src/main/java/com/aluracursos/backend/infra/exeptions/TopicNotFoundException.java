package com.aluracursos.backend.infra.exeptions;


public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(Long id) {
        super("Topic with ID " + id + " not found" + id);
    }
}

