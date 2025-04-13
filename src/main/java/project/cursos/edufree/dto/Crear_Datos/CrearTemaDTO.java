package project.cursos.edufree.dto.Crear_Datos;

import jakarta.validation.constraints.NotBlank;

public class CrearTemaDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    private String descripcion;
    private Integer cursoId;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }
}
