package project.cursos.edufree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.cursos.edufree.model.Rol;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>  {
    Optional<Rol> findByNombre(String nombre);
}
