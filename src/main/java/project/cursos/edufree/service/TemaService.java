package project.cursos.edufree.service;

import org.springframework.stereotype.Service;
import project.cursos.edufree.dto.Crear_Datos.CrearTemaDTO;
import project.cursos.edufree.exception.ResourceNotFoundException;
import project.cursos.edufree.model.Curso;
import project.cursos.edufree.model.Tema;
import project.cursos.edufree.repository.CursoRepository;
import project.cursos.edufree.repository.TemaRepository;

import java.util.List;

@Service
public class TemaService {

    private final TemaRepository temaRepository;
    private final CursoRepository cursoRepository;

    public TemaService(TemaRepository temaRepository, CursoRepository cursoRepository) {
        this.temaRepository = temaRepository;
        this.cursoRepository = cursoRepository;
    }


    public List<Tema> getAllTemas() {
        return temaRepository.findAll();
    }


    public List<Tema> getTemasByCurso(Integer cursoId) {
        return temaRepository.findByCursoId(cursoId);
    }


    public Tema saveTema(Tema tema) {
        return temaRepository.save(tema);
    }


    public void deleteTema(Integer id) {
        temaRepository.deleteById(id);
    }


    public Tema obtenerPorId(Integer id) {
        return temaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tema con ID " + id + " no encontrado"));
    }

    public Tema crearTema(String nombre, String descripcion, Integer cursoId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso con ID " + cursoId + " no encontrado"));

        Tema tema = new Tema();
        tema.setNombre(nombre);
        tema.setDescripcion(descripcion);
        tema.setCurso(curso);

        return temaRepository.save(tema);
    }

    public Tema actualizarTema(Integer id, CrearTemaDTO dto) {
        Tema tema = obtenerPorId(id);
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso con ID " + dto.getCursoId() + " no encontrado"));

        tema.setNombre(dto.getNombre());
        tema.setDescripcion(dto.getDescripcion());
        tema.setCurso(curso);

        return temaRepository.save(tema);
    }

    public Tema actualizarParcial(Integer id, CrearTemaDTO dto) {
        Tema tema = obtenerPorId(id);

        if (dto.getNombre() != null) tema.setNombre(dto.getNombre());
        if (dto.getDescripcion() != null) tema.setDescripcion(dto.getDescripcion());
        if (dto.getCursoId() != null) {
            Curso curso = cursoRepository.findById(dto.getCursoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Curso con ID " + dto.getCursoId() + " no encontrado"));
            tema.setCurso(curso);
        }

        return temaRepository.save(tema);
    }

}
