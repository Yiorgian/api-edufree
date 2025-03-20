package project.cursos.edufree.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.cursos.edufree.model.Usuario;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Buscar usuario por email (para autenticación)
    Optional<Usuario> findByEmail(String email);
}
