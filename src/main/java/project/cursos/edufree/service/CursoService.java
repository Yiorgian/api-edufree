package project.cursos.edufree.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.cursos.edufree.dto.CursoDTO;
import project.cursos.edufree.model.Curso;
import project.cursos.edufree.model.Usuario;
import project.cursos.edufree.repository.CursoRepository;
import project.cursos.edufree.repository.UsuarioRepository;
import project.cursos.edufree.exception.ResourceNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import project.cursos.edufree.dto.Crear_Datos.CrearCursoDTO;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public CursoService(CursoRepository cursoRepository, UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }


    public List<Curso> obtenerPorAdministrador(Integer administradorId) {
        return cursoRepository.findByAdministradorId(administradorId);
    }

    public Curso obtenerPorId(Integer id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso con ID " + id + " no encontrado"));
    }


    public Curso crearCurso(String nombre, String descripcion, BigDecimal precio, Integer administradorId) {
        Optional<Usuario> adminOpt = usuarioRepository.findById(administradorId);
        if (adminOpt.isPresent()) {
            Curso curso = new Curso(nombre, descripcion, precio, adminOpt.get());
            return cursoRepository.save(curso);
        } else {
            throw new RuntimeException("Administrador no encontrado");
        }
    }

    public Curso actualizarCurso(Integer id, String nombre, String descripcion, BigDecimal precio, Integer administradorId) {
        Optional<Curso> cursoOpt = cursoRepository.findById(id);
        Optional<Usuario> adminOpt = usuarioRepository.findById(administradorId);

        if (cursoOpt.isPresent() && adminOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            curso.setNombre(nombre);
            curso.setDescripcion(descripcion);
            curso.setPrecio(precio);
            curso.setAdministrador(adminOpt.get());
            return cursoRepository.save(curso);
        } else {
            throw new RuntimeException("Curso o Administrador no encontrado");
        }
    }

    public Curso actualizarCursoParcial(Integer id, CrearCursoDTO dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso con ID " + id + " no encontrado"));

        if (dto.getNombre() != null) curso.setNombre(dto.getNombre());
        if (dto.getDescripcion() != null) curso.setDescripcion(dto.getDescripcion());
        if (dto.getPrecio() != null) curso.setPrecio(dto.getPrecio());

        if (dto.getAdministradorId() != null) {
            Usuario admin = usuarioRepository.findById(dto.getAdministradorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Administrador con ID " + dto.getAdministradorId() + " no encontrado"));
            curso.setAdministrador(admin);
        }

        return cursoRepository.save(curso);
    }


    public void eliminarCurso(Integer id) {
        cursoRepository.deleteById(id);
    }

    public List<CursoDTO> obtenerTodosComoDTO() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream()
                .map(curso -> modelMapper.map(curso, CursoDTO.class))
                .collect(Collectors.toList());
    }
}
