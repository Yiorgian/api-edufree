package project.cursos.edufree.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.cursos.edufree.model.Curso;
import project.cursos.edufree.model.Inscripcion;
import project.cursos.edufree.model.Usuario;
import project.cursos.edufree.repository.CursoRepository;
import project.cursos.edufree.repository.InscripcionRepository;
import project.cursos.edufree.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;


    private final UsuarioRepository usuarioRepository;


    private final CursoRepository cursoRepository;

    public InscripcionService(InscripcionRepository inscripcionRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.inscripcionRepository = inscripcionRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<Inscripcion> obtenerTodas() {
        return inscripcionRepository.findAll();
    }

    public Optional<Inscripcion> obtenerPorId(Integer id) {
        return inscripcionRepository.findById(id);
    }

    public List<Inscripcion> obtenerPorUsuario(Integer usuarioId) {
        return inscripcionRepository.findByUsuarioId(usuarioId);
    }

    public List<Inscripcion> obtenerPorCurso(Integer cursoId) {
        return inscripcionRepository.findByCursoId(cursoId);
    }

    public Inscripcion crearInscripcion(Integer usuarioId, Integer cursoId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);

        if (usuarioOpt.isPresent() && cursoOpt.isPresent()) {

            Optional<Inscripcion> existente = inscripcionRepository.findByUsuarioIdAndCursoId(usuarioId, cursoId);
            if (existente.isPresent()) {
                throw new RuntimeException("El usuario ya está inscrito en este curso");
            }

            Inscripcion inscripcion = new Inscripcion(usuarioOpt.get(), cursoOpt.get());
            return inscripcionRepository.save(inscripcion);
        } else {
            throw new RuntimeException("Usuario o Curso no encontrado");
        }
    }

    public void eliminarInscripcion(Integer id) {
        inscripcionRepository.deleteById(id);
    }
}
