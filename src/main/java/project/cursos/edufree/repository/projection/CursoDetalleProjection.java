package project.cursos.edufree.repository.projection;

import project.cursos.edufree.dto.TemaDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CursoDetalleProjection {
    Integer getId();
    String getNombre();
    String getDescripcion();
    BigDecimal getPrecio();
    String getFechaCreacion();
    String getAdministrador();
    List<TemaDTO> getTemas();
}
