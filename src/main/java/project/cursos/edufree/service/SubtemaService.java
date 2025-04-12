package project.cursos.edufree.service;

import org.springframework.stereotype.Service;
import project.cursos.edufree.dto.Crear_Datos.CrearSubtemaDTO;
import project.cursos.edufree.exception.ResourceNotFoundException;
import project.cursos.edufree.model.Subtema;

import project.cursos.edufree.model.Tema;
import project.cursos.edufree.repository.SubtemaRepository;
import project.cursos.edufree.repository.TemaRepository;

import java.util.List;

@Service
public class SubtemaService {

    private final SubtemaRepository subtemaRepository;
    private final TemaRepository temaRepository;

    public SubtemaService(SubtemaRepository subtemaRepository, TemaRepository temaRepository) {
        this.subtemaRepository = subtemaRepository;
        this.temaRepository = temaRepository;
    }

    public List<Subtema> getAllSubtemas() {
        return subtemaRepository.findAll();
    }

    public List<Subtema> getSubtemasByTema(Integer temaId) {
        return subtemaRepository.findByTemaId(temaId);
    }

    public Subtema saveSubtema(Subtema subtema) {
        return subtemaRepository.save(subtema);
    }

    public void deleteSubtema(Integer id) {
        subtemaRepository.deleteById(id);
    }

    public Subtema obtenerPorId(Integer id) {
        return subtemaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subtema con ID " + id + " no encontrado"));
    }

    public Subtema crearSubtema(String nombre, Integer tiempo, Integer temaId) {
        Tema tema = temaRepository.findById(temaId)
                .orElseThrow(() -> new ResourceNotFoundException("Tema con ID " + temaId + " no encontrado"));

        Subtema subtema = new Subtema();
        subtema.setNombre(nombre);
        subtema.setTiempo(tiempo);
        subtema.setTema(tema);
        return subtemaRepository.save(subtema);
    }

    public Subtema actualizarSubtema(Integer id, CrearSubtemaDTO dto) {
        Subtema subtema = subtemaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subtema con ID " + id + " no encontrado"));

        Tema tema = temaRepository.findById(dto.getTemaId())
                .orElseThrow(() -> new ResourceNotFoundException("Tema con ID " + dto.getTemaId() + " no encontrado"));

        subtema.setNombre(dto.getNombre());
        subtema.setTiempo(dto.getTiempo());
        subtema.setTema(tema);

        return subtemaRepository.save(subtema);
    }

    public Subtema actualizarParcial(Integer id, CrearSubtemaDTO dto) {
        Subtema subtema = subtemaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subtema con ID " + id + " no encontrado"));

        if (dto.getNombre() != null) subtema.setNombre(dto.getNombre());
        if (dto.getTiempo() != null) subtema.setTiempo(dto.getTiempo());
        if (dto.getTemaId() != null) {
            Tema tema = temaRepository.findById(dto.getTemaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tema con ID " + dto.getTemaId() + " no encontrado"));
            subtema.setTema(tema);
        }

        return subtemaRepository.save(subtema);
    }


}
