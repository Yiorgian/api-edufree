package project.cursos.edufree.dto.Filtrar_Datos;
import java.math.BigDecimal;

public class CursoDetalleDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String fechaCreacion;
    private String administrador;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
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
    public String getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public String getAdministrador() {
        return administrador;
    }
    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }
}
