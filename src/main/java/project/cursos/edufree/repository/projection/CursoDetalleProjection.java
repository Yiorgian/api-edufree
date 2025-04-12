package project.cursos.edufree.repository.projection;

import java.math.BigDecimal;

public interface CursoDetalleProjection {
    Integer getId();
    String getNombre();
    String getDescripcion();
    BigDecimal getPrecio();
    String getFechaCreacion();
    String getAdministrador();
}
