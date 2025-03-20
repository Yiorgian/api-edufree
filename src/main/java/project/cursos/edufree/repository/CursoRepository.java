package project.cursos.edufree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.cursos.edufree.model.Curso;

import java.util.List;

@Repository
public interface CursoRepository  extends JpaRepository<Curso, Integer>{
    List<Curso> findByAdministradorId(Integer administradorId);
}
