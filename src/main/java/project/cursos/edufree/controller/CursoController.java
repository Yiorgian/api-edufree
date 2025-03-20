package project.cursos.edufree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.cursos.edufree.model.Curso;
import project.cursos.edufree.service.CursoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> obtenerTodos() {
        return ResponseEntity.ok(cursoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerPorId(@PathVariable Integer id) {
        Optional<Curso> curso = cursoService.obtenerPorId(id);
        return curso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/administrador/{administradorId}")
    public ResponseEntity<List<Curso>> obtenerPorAdministrador(@PathVariable Integer administradorId) {
        return ResponseEntity.ok(cursoService.obtenerPorAdministrador(administradorId));
    }

    @PostMapping
    public ResponseEntity<Curso> crearCurso(
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam Integer administradorId) {
        Curso curso = cursoService.crearCurso(nombre, descripcion, administradorId);
        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(
            @PathVariable Integer id,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam Integer administradorId) {
        Curso curso = cursoService.actualizarCurso(id, nombre, descripcion, administradorId);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Integer id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
