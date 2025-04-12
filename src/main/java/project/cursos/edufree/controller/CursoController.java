package project.cursos.edufree.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.cursos.edufree.dto.Crear_Datos.CrearCursoDTO;
import project.cursos.edufree.dto.Filtrar_Datos.CursoDetalleDTO;
import project.cursos.edufree.model.Curso;
import project.cursos.edufree.service.CursoService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
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

    @GetMapping("/detalle/{cursoId}")
    public ResponseEntity<CursoDetalleDTO> obtenerCursoDetalle(@PathVariable Integer cursoId) {
        CursoDetalleDTO detalle = cursoService.obtenerCursoDetalle(cursoId);
        return ResponseEntity.ok(detalle);
    }

    @GetMapping("/precio")
    public ResponseEntity<List<CursoDetalleDTO>> filtrarCursosPorPrecio(
            @RequestParam("min") BigDecimal precioMin,
            @RequestParam("max") BigDecimal precioMax) {
        List<CursoDetalleDTO> cursos = cursoService.filtrarCursosPorPrecio(precioMin, precioMax);
        return ResponseEntity.ok(cursos);
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
        Curso curso = cursoService.actualizarCurso(id, dto.getNombre(), dto.getDescripcion(), dto.getPrecio(), dto.getAdministradorId()
        );
        return ResponseEntity.ok(curso);
    }

    @GetMapping("/administrador")
    public ResponseEntity<List<CursoDetalleDTO>> filtrarCursosPorAdministrador(
            @RequestParam("id") Integer administradorId) {
        List<CursoDetalleDTO> cursos = cursoService.filtrarCursosPorAdministrador(administradorId);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/palabra")
    public ResponseEntity<List<CursoDetalleDTO>> filtrarCursosPorPalabraClave(@RequestParam("palabra") String palabra) {
        List<CursoDetalleDTO> cursos = cursoService.filtrarCursosPorPalabraClave(palabra);
        return ResponseEntity.ok(cursos);
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
