package project.cursos.edufree.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.cursos.edufree.dto.Crear_Datos.CrearTemaDTO;
import project.cursos.edufree.model.Tema;
import project.cursos.edufree.service.TemaService;

import java.util.List;

@RestController
@RequestMapping("/api/temas")
public class TemaController {

    private final TemaService temaService;

    public TemaController(TemaService temaService) {
        this.temaService = temaService;
    }

    @GetMapping
    public ResponseEntity<List<Tema>> getAllTemas() {
        return ResponseEntity.ok(temaService.getAllTemas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tema> getTemaById(@PathVariable Integer id) {
        Tema tema = temaService.obtenerPorId(id);
        return ResponseEntity.ok(tema);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Tema>> getTemasByCurso(@PathVariable Integer cursoId) {
        return ResponseEntity.ok(temaService.getTemasByCurso(cursoId));
    }

    @PostMapping
    public ResponseEntity<Tema> crearTema(@Valid @RequestBody CrearTemaDTO dto) {
        Tema tema = temaService.crearTema(dto.getNombre(), dto.getDescripcion(), dto.getCursoId());
        return ResponseEntity.ok(tema);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tema> actualizarTema(@PathVariable Integer id, @RequestBody CrearTemaDTO dto) {
        Tema tema = temaService.actualizarTema(id, dto);
        return ResponseEntity.ok(tema);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Tema> actualizarParcialTema(@PathVariable Integer id, @RequestBody CrearTemaDTO dto) {
        Tema tema = temaService.actualizarParcial(id, dto);
        return ResponseEntity.ok(tema);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTema(@PathVariable Integer id) {
        temaService.deleteTema(id);
        return ResponseEntity.noContent().build();
    }
}
