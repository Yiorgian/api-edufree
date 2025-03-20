package project.cursos.edufree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.cursos.edufree.model.Curso;
import project.cursos.edufree.model.Usuario;
import project.cursos.edufree.repository.CursoRepository;
import project.cursos.edufree.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service

public class CursoService {

    private final CursoRepository cursoRepository;

    private final UsuarioRepository usuarioRepository;

    public CursoService(CursoRepository cursoRepository, UsuarioRepository usuarioRepository) {
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> obtenerPorId(Integer id) {
        return cursoRepository.findById(id);
    }

    public List<Curso> obtenerPorAdministrador(Integer administradorId) {
        return cursoRepository.findByAdministradorId(administradorId);
    }

    public Curso crearCurso(String nombre, String descripcion, Integer administradorId) {
        Optional<Usuario> adminOpt = usuarioRepository.findById(administradorId);
        if (adminOpt.isPresent()) {
            Curso curso = new Curso(nombre, descripcion, adminOpt.get());
            return cursoRepository.save(curso);
        } else {
            throw new RuntimeException("Administrador no encontrado");
        }
    }

    public Curso actualizarCurso(Integer id, String nombre, String descripcion, Integer administradorId) {
        Optional<Curso> cursoOpt = cursoRepository.findById(id);
        Optional<Usuario> adminOpt = usuarioRepository.findById(administradorId);

        if (cursoOpt.isPresent() && adminOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            curso.setNombre(nombre);
            curso.setDescripcion(descripcion);
            curso.setAdministrador(adminOpt.get());
            return cursoRepository.save(curso);
        } else {
            throw new RuntimeException("Curso o Administrador no encontrado");
        }
    }

    public void eliminarCurso(Integer id) {
        cursoRepository.deleteById(id);
    }
}
