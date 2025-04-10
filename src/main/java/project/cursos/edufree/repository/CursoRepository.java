package project.cursos.edufree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.cursos.edufree.model.Curso;
import project.cursos.edufree.repository.projection.CursoAdministradorProjection;
import project.cursos.edufree.repository.projection.CursoDetalleProjection;
import project.cursos.edufree.repository.projection.CursoPalabraClaveProjection;
import project.cursos.edufree.repository.projection.CursoPrecioProjection;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CursoRepository  extends JpaRepository<Curso, Integer>{
    List<Curso> findByAdministradorId(Integer administradorId);

    @Query(value = "CALL sp_getCursoDetalle(:cursoId)", nativeQuery = true)
    CursoDetalleProjection obtenerCursoDetalle(@Param("cursoId") Integer cursoId);

    @Query(value = "CALL sp_filtrarCursosPorPrecio(:p_precioMin, :p_precioMax)", nativeQuery = true)
    List<CursoPrecioProjection> filtrarCursosPorPrecio(
            @Param("p_precioMin") BigDecimal p_precioMin,
            @Param("p_precioMax") BigDecimal p_precioMax
    );

    @Query(value = "CALL sp_filtrarCursosPorAdministrador(:p_administradorId)", nativeQuery = true)
    List<CursoAdministradorProjection> filtrarCursosPorAdministrador(
            @Param("p_administradorId") Integer p_administradorId
    );


    @Query(value = "CALL sp_filtrarCursosPorPalabraClave(:p_palabra)", nativeQuery = true)
    List<CursoPalabraClaveProjection> filtrarCursosPorPalabraClave(@Param("p_palabra") String p_palabra);

}
