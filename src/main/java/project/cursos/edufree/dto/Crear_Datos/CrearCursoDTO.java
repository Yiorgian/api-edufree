package project.cursos.edufree.dto.Crear_Datos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CrearCursoDTO {


    @NotBlank(message = "El nombre del curso es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio del curso es obligatorio")
    private BigDecimal precio;

    @NotNull(message = "El ID del administrador es obligatorio")
    private Integer administradorId;


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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getAdministradorId() {
        return administradorId;
    }

    public void setAdministradorId(Integer administradorId) {
        this.administradorId = administradorId;
    }
}