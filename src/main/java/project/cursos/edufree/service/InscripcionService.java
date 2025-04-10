package project.cursos.edufree.service;
import org.springframework.stereotype.Service;
import project.cursos.edufree.dto.Filtrar_Datos.ResumenInscripcionDTO;
import project.cursos.edufree.exception.BadRequestException;
import project.cursos.edufree.exception.ConflictException;
import project.cursos.edufree.exception.ResourceNotFoundException;
import project.cursos.edufree.model.Curso;
import project.cursos.edufree.model.Inscripcion;
import project.cursos.edufree.model.Usuario;
import project.cursos.edufree.repository.CursoRepository;
import project.cursos.edufree.repository.InscripcionRepository;
import project.cursos.edufree.repository.UsuarioRepository;
import project.cursos.edufree.dto.InscripcionDTO;
import project.cursos.edufree.dto.UsuarioDTO;
import project.cursos.edufree.dto.CursoSimpleDTO;
import project.cursos.edufree.dto.RolDTO;
import java.util.List;

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

    public Inscripcion obtenerPorId(Integer id) {
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción con ID " + id + " no encontrada"));
    }


    public List<Inscripcion> obtenerPorUsuario(Integer usuarioId) {
        return inscripcionRepository.findByUsuarioId(usuarioId);
    }

    public List<Inscripcion> obtenerPorCurso(Integer cursoId) {
        return inscripcionRepository.findByCursoId(cursoId);
    }


    public List<ResumenInscripcionDTO> obtenerInscripcionesPorUsuario(Integer usuarioId) {
        return inscripcionRepository.obtenerInscripcionesPorUsuario(usuarioId)
                .stream()
                .map(p -> {
                    ResumenInscripcionDTO dto = new ResumenInscripcionDTO();
                    dto.setId(p.getId());
                    dto.setUsuario(p.getUsuario());
                    dto.setCurso(p.getCurso());
                    dto.setMetodoPago(p.getMetodoPago());
                    dto.setFechaInscripcion(p.getFechaInscripcion());
                    return dto;
                }).toList();
    }

    public Inscripcion crearInscripcion(Usuario usuario, Curso curso, String metodoPago) {
        if (usuario == null || curso == null || metodoPago == null || metodoPago.isEmpty()) {
            throw new BadRequestException("Debe proporcionar usuario, curso y método de pago");
        }

        Usuario usuarioExistente = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + usuario.getId() + " no encontrado"));

        Curso cursoExistente = cursoRepository.findById(curso.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso con ID " + curso.getId() + " no encontrado"));

        if (inscripcionRepository.findByUsuarioIdAndCursoId(usuario.getId(), curso.getId()).isPresent()) {
            throw new ConflictException("El usuario ya está inscrito en este curso");
        }

        Inscripcion inscripcion = new Inscripcion(usuarioExistente, cursoExistente, metodoPago);
        return inscripcionRepository.save(inscripcion);
    }



    public void eliminarInscripcion(Integer id) {
        inscripcionRepository.deleteById(id);
    }


    public List<InscripcionDTO> obtenerTodasComoDTO() {
        return inscripcionRepository.findAll().stream().map(inscripcion -> {
            InscripcionDTO dto = new InscripcionDTO();
            dto.setId(inscripcion.getId());
            dto.setFechaInscripcion(inscripcion.getFechaInscripcion());
            Usuario usuario = inscripcion.getUsuario();
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setNombre(usuario.getNombre());
            usuarioDTO.setEmail(usuario.getEmail());

            RolDTO rolDTO = new RolDTO();
            rolDTO.setId(usuario.getRol().getId());
            rolDTO.setNombre(usuario.getRol().getNombre());
            usuarioDTO.setRol(rolDTO);

            dto.setUsuario(usuarioDTO);

            Curso curso = inscripcion.getCurso();
            CursoSimpleDTO cursoDTO = new CursoSimpleDTO();
            cursoDTO.setId(curso.getId());
            cursoDTO.setNombre(curso.getNombre());

            dto.setCurso(cursoDTO);

            return dto;
        }).toList();
    }


}
