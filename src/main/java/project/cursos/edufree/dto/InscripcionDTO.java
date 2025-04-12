package project.cursos.edufree.dto;

import java.time.LocalDateTime;

public class InscripcionDTO {
    private Integer id;
    private UsuarioDTO usuario;
    private CursoSimpleDTO curso;
    private LocalDateTime fechaInscripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public CursoSimpleDTO getCurso() {
        return curso;
    }

    public void setCurso(CursoSimpleDTO curso) {
        this.curso = curso;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}
