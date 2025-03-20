package project.cursos.edufree.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.cursos.edufree.model.Inscripcion;

import java.util.List;
import java.util.Optional;
@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer>  {
    List<Inscripcion> findByUsuarioId(Integer usuarioId);
    List<Inscripcion> findByCursoId(Integer cursoId);
    Optional<Inscripcion> findByUsuarioIdAndCursoId(Integer usuarioId, Integer cursoId);
}
