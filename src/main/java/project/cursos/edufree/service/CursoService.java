package project.cursos.edufree.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.cursos.edufree.dto.Filtrar_Datos.CursoDetalleDTO;
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
import project.cursos.edufree.repository.projection.CursoAdministradorProjection;
import project.cursos.edufree.repository.projection.CursoDetalleProjection;
import project.cursos.edufree.repository.projection.CursoPalabraClaveProjection;
import project.cursos.edufree.repository.projection.CursoPrecioProjection;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;


    public CursoService(CursoRepository cursoRepository, UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }


    public Curso obtenerPorId(Integer id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso con ID " + id + " no encontrado"));
    }

    public CursoDetalleDTO obtenerCursoDetalle(Integer cursoId) {
        CursoDetalleProjection p = cursoRepository.obtenerCursoDetalle(cursoId);
        CursoDetalleDTO dto = new CursoDetalleDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setDescripcion(p.getDescripcion());
        dto.setPrecio(p.getPrecio());
        dto.setFechaCreacion(p.getFechaCreacion());
        dto.setAdministrador(p.getAdministrador());
        return dto;
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
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso con ID " + id + " no encontrado"));

        Usuario administrador = usuarioRepository.findById(administradorId)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador con ID " + administradorId + " no encontrado"));

        curso.setNombre(nombre);
        curso.setDescripcion(descripcion);
        curso.setPrecio(precio);
        curso.setAdministrador(administrador);

        return cursoRepository.save(curso);
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

    public List<CursoDetalleDTO> filtrarCursosPorPrecio(BigDecimal precioMin, BigDecimal precioMax) {
        List<CursoPrecioProjection> projections = cursoRepository.filtrarCursosPorPrecio(precioMin, precioMax);
        return projections.stream().map(proj -> {
            CursoDetalleDTO dto = new CursoDetalleDTO();
            dto.setId(proj.getId());
            dto.setNombre(proj.getNombre());
            dto.setDescripcion(proj.getDescripcion());
            dto.setPrecio(BigDecimal.valueOf(proj.getPrecio()));
            dto.setFechaCreacion(proj.getFecha_creacion());
            dto.setAdministrador(proj.getAdministrador());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<CursoDetalleDTO> filtrarCursosPorAdministrador(Integer administradorId) {
        List<CursoAdministradorProjection> projections =
                cursoRepository.filtrarCursosPorAdministrador(administradorId);
        return projections.stream().map(proj -> {
            CursoDetalleDTO dto = new CursoDetalleDTO();
            dto.setId(proj.getId());
            dto.setNombre(proj.getNombre());
            dto.setDescripcion(proj.getDescripcion());
            dto.setPrecio(BigDecimal.valueOf(proj.getPrecio()));
            dto.setFechaCreacion(proj.getFecha_creacion());
            dto.setAdministrador(proj.getAdministrador());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<CursoDetalleDTO> filtrarCursosPorPalabraClave(String palabra) {
        List<CursoPalabraClaveProjection> projections = cursoRepository.filtrarCursosPorPalabraClave(palabra);
        return projections.stream().map(proj -> {
            CursoDetalleDTO dto = new CursoDetalleDTO();
            dto.setId(proj.getId());
            dto.setNombre(proj.getNombre());
            dto.setDescripcion(proj.getDescripcion());
            dto.setPrecio(BigDecimal.valueOf(proj.getPrecio()));
            dto.setFechaCreacion(proj.getFecha_creacion());
            dto.setAdministrador(proj.getAdministrador());
            return dto;
        }).collect(Collectors.toList());
    }


    public void eliminarCurso(Integer id) {
        cursoRepository.deleteById(id);
    }


}
