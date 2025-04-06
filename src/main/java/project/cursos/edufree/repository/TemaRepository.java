package project.cursos.edufree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.cursos.edufree.model.Tema;

import java.util.List;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Integer> {
    List<Tema> findByCursoId(Integer cursoId);
}