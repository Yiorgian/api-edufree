package project.cursos.edufree.repository.projection;
import jakarta.validation.Valid;

public interface CursoAdministradorProjection {
    Integer getId();
    String getNombre();
    String getDescripcion();
    Double getPrecio();
    String getFecha_creacion();
    String getAdministrador();
}
