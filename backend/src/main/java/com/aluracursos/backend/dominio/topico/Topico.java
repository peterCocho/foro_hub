package com.aluracursos.backend.dominio.topico;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "status")
    private String status = "ACTIVO";

    @NotNull
    @Column(name = "autor_id")
    private Long autorId;

    @NotNull
    @Column(name = "curso_id")
    private Long cursoId;

    //  Se ejecuta antes de persistir si fechaCreacion está vacía
    @PrePersist
    public void prePersist() {
        if (this.fechaCreacion == null) {
            this.fechaCreacion = LocalDateTime.now();
        }
    }

    // Constructores
    public Topico(Object o, @NotBlank String titulo, @NotBlank String mensaje, TopicoStatus activo, LocalDateTime now, @NotNull Long aLong, @NotNull Long cursoId) {}

    public Topico(String titulo, String mensaje, Long autorId, Long cursoId) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.autorId = autorId;
        this.cursoId = cursoId;
    }

    public Topico() {
        // Constructor requerido por JPA
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public TopicoStatus getStatus() {
        return TopicoStatus.valueOf(status);
    }

    public void setStatus(TopicoStatus status) {
        this.status = status.name();
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }
}
