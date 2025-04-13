package project.cursos.edufree.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.cursos.edufree.model.Inscripcion;
import project.cursos.edufree.repository.projection.ResumenInscripcionProjection;

import java.util.List;
import java.util.Optional;
@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer>  {
    Optional<Inscripcion> findByUsuarioIdAndCursoId(Integer usuarioId, Integer cursoId);

    @Query(value = "CALL sp_getInscripciones(:usuarioId)", nativeQuery = true)
    List<ResumenInscripcionProjection> obtenerInscripcionesPorUsuario(
            @Param("usuarioId") Integer usuarioId
    );
}
