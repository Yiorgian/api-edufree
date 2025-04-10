package project.cursos.edufree.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.cursos.edufree.dto.Filtrar_Datos.ResumenInscripcionDTO;
import project.cursos.edufree.model.Curso;
import project.cursos.edufree.model.Inscripcion;
import project.cursos.edufree.model.Usuario;
import project.cursos.edufree.service.InscripcionService;
import project.cursos.edufree.dto.InscripcionDTO;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }


    @GetMapping("/dto")
    public ResponseEntity<List<InscripcionDTO>> obtenerTodasDTO() {
        return ResponseEntity.ok(inscripcionService.obtenerTodasComoDTO());}

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
    public ResponseEntity<List<ResumenInscripcionDTO>> obtenerInscripcionesPorUsuario(@PathVariable Integer usuarioId) {
        List<ResumenInscripcionDTO> inscripciones = inscripcionService.obtenerInscripcionesPorUsuario(usuarioId);
        return ResponseEntity.ok(inscripciones);
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


}
