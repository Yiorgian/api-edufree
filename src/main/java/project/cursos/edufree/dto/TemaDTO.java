package project.cursos.edufree.dto;

import java.util.List;

public class TemaDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private List<SubtemaDTO> subtemas;


    public List<SubtemaDTO> getSubtemas() {
        return subtemas;
    }

    public void setSubtemas(List<SubtemaDTO> subtemas) {
        this.subtemas = subtemas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }




}
