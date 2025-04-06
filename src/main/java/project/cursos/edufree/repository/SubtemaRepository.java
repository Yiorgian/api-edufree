package project.cursos.edufree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.cursos.edufree.model.Subtema;

import java.util.List;

@Repository
public interface SubtemaRepository extends JpaRepository<Subtema, Integer> {
    List<Subtema> findByTemaId(Integer temaId);
}