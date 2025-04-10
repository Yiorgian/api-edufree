package project.cursos.edufree.repository.projection;
import java.math.BigDecimal;

public interface CursoPrecioProjection {
    Integer getId();
    String getNombre();
    String getDescripcion();
    Double getPrecio();
    String getFecha_creacion();
    String getAdministrador();
}
