package project.cursos.edufree.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.cursos.edufree.model.Inscripcion;
import project.cursos.edufree.service.InscripcionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {
    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public ResponseEntity<List<Inscripcion>> obtenerTodas() {
        return ResponseEntity.ok(inscripcionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtenerPorId(@PathVariable Integer id) {
        Optional<Inscripcion> inscripcion = inscripcionService.obtenerPorId(id);
        return inscripcion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Inscripcion>> obtenerPorUsuario(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(inscripcionService.obtenerPorUsuario(usuarioId));
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Inscripcion>> obtenerPorCurso(@PathVariable Integer cursoId) {
        return ResponseEntity.ok(inscripcionService.obtenerPorCurso(cursoId));
    }

    @PostMapping
    public ResponseEntity<Inscripcion> crearInscripcion(
            @RequestParam Integer usuarioId,
            @RequestParam Integer cursoId) {
        Inscripcion inscripcion = inscripcionService.crearInscripcion(usuarioId, cursoId);
        return ResponseEntity.ok(inscripcion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable Integer id) {
        inscripcionService.eliminarInscripcion(id);
        return ResponseEntity.noContent().build();
    }
}
