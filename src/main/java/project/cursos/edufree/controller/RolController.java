package project.cursos.edufree.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.cursos.edufree.model.Rol;
import project.cursos.edufree.service.RolService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RolController {


    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> obtenerTodos() {
        return ResponseEntity.ok(rolService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerPorId(@PathVariable Integer id) {
        Optional<Rol> rol = rolService.obtenerPorId(id);
        return rol.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Rol> obtenerPorNombre(@PathVariable String nombre) {
        Optional<Rol> rol = rolService.obtenerPorNombre(nombre);
        return rol.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestParam String nombre) {
        Rol rol = rolService.crearRol(nombre);
        return ResponseEntity.ok(rol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(
            @PathVariable Integer id,
            @RequestParam String nombre) {
        Rol rol = rolService.actualizarRol(id, nombre);
        return ResponseEntity.ok(rol);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Integer id) {
        rolService.eliminarRol(id);
        return ResponseEntity.noContent().build();
    }
}
