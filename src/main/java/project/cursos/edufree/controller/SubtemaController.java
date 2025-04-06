package project.cursos.edufree.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.cursos.edufree.dto.Crear_Datos.CrearSubtemaDTO;
import project.cursos.edufree.model.Subtema;
import project.cursos.edufree.service.SubtemaService;
import project.cursos.edufree.dto.SubtemaDTO;
import java.util.List;

@RestController
@RequestMapping("/api/subtemas")
public class SubtemaController {

    private final SubtemaService subtemaService;

    public SubtemaController(SubtemaService subtemaService) {
        this.subtemaService = subtemaService;
    }

    @GetMapping
    public ResponseEntity<List<Subtema>> getAllSubtemas() {
        return ResponseEntity.ok(subtemaService.getAllSubtemas());
    }

    @GetMapping("/tema/{temaId}")
    public ResponseEntity<List<Subtema>> getSubtemasByTema(@PathVariable Integer temaId) {
        return ResponseEntity.ok(subtemaService.getSubtemasByTema(temaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subtema> obtenerPorId(@PathVariable Integer id) {
        Subtema subtema = subtemaService.obtenerPorId(id);
        return ResponseEntity.ok(subtema);
    }


    @PostMapping
    public ResponseEntity<Subtema> crear(@RequestBody CrearSubtemaDTO dto) {
        Subtema subtema = subtemaService.crearSubtema(dto.getNombre(), dto.getTiempo(), dto.getTemaId());
        return ResponseEntity.ok(subtema);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subtema> actualizar(@PathVariable Integer id, @RequestBody CrearSubtemaDTO dto) {
        Subtema subtema = subtemaService.actualizarSubtema(id, dto);
        return ResponseEntity.ok(subtema);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Subtema> actualizarParcial(@PathVariable Integer id, @RequestBody CrearSubtemaDTO dto) {
        Subtema subtema = subtemaService.actualizarParcial(id, dto);
        return ResponseEntity.ok(subtema);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSubtema(@PathVariable Integer id) {
        subtemaService.deleteSubtema(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dto")
    public ResponseEntity<List<SubtemaDTO>> listarSubtemas() {
        return ResponseEntity.ok(subtemaService.obtenerTodosComoDTO());
    }
}
