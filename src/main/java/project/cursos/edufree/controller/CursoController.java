package project.cursos.edufree.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.cursos.edufree.dto.Crear_Datos.CrearCursoDTO;
import project.cursos.edufree.dto.CursoDTO;
import project.cursos.edufree.model.Curso;
import project.cursos.edufree.service.CursoService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/dto")
    public ResponseEntity<List<CursoDTO>> listarCursosConTemasYSubtemas() {
        return ResponseEntity.ok(cursoService.obtenerTodosComoDTO());
    }

    @GetMapping
    public ResponseEntity<List<Curso>> obtenerTodos() {
        return ResponseEntity.ok(cursoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerPorId(@PathVariable Integer id) {
        Curso curso = cursoService.obtenerPorId(id);
        return ResponseEntity.ok(curso);
    }


    @GetMapping("/administrador/{administradorId}")
    public ResponseEntity<List<Curso>> obtenerPorAdministrador(@PathVariable Integer administradorId) {
        return ResponseEntity.ok(cursoService.obtenerPorAdministrador(administradorId));
    }

    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody CrearCursoDTO dto) {
        Curso curso = cursoService.crearCurso(
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getPrecio(),
                dto.getAdministradorId()
        );
        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(
            @PathVariable Integer id,
            @RequestBody CrearCursoDTO dto) {
        Curso curso = cursoService.actualizarCurso(
                id,
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getPrecio(),
                dto.getAdministradorId()
        );
        return ResponseEntity.ok(curso);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Curso> actualizarParcialCurso(
            @PathVariable Integer id,
            @RequestBody CrearCursoDTO dto) {
        Curso curso = cursoService.actualizarCursoParcial(id, dto);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Integer id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
