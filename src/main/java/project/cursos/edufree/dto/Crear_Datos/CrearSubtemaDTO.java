package project.cursos.edufree.dto.Crear_Datos;

import jakarta.validation.constraints.NotBlank;

public class CrearSubtemaDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private Integer tiempo;

    private Integer temaId;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getTemaId() {
        return temaId;
    }

    public void setTemaId(Integer temaId) {
        this.temaId = temaId;
    }
}
