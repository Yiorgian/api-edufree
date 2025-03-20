package project.cursos.edufree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.cursos.edufree.model.Notificacion;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer>{
    List<Notificacion> findByUsuarioId(Integer usuarioId);
}
