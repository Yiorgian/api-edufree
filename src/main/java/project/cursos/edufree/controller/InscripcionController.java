package project.cursos.edufree.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.cursos.edufree.model.Curso;
import project.cursos.edufree.model.Inscripcion;
import project.cursos.edufree.model.Usuario;
import project.cursos.edufree.service.InscripcionService;
import project.cursos.edufree.dto.InscripcionDTO;

import java.util.List;
import java.util.Map;

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
        Inscripcion inscripcion = inscripcionService.obtenerPorId(id);
        return ResponseEntity.ok(inscripcion);
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
    public ResponseEntity<Inscripcion> crearInscripcion(@RequestBody Inscripcion inscripcion) {
        Usuario usuario = new Usuario();
        usuario.setId(inscripcion.getUsuario().getId());

        Curso curso = new Curso();
        curso.setId(inscripcion.getCurso().getId());

        String metodoPago = inscripcion.getMetodoPago();

        Inscripcion nueva = inscripcionService.crearInscripcion(usuario, curso, metodoPago);
        return ResponseEntity.ok(nueva);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable Integer id) {
        inscripcionService.eliminarInscripcion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dto")
    public ResponseEntity<List<InscripcionDTO>> obtenerTodasDTO() {
        return ResponseEntity.ok(inscripcionService.obtenerTodasComoDTO());}
}
