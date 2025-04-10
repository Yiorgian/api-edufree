package project.cursos.edufree.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.cursos.edufree.model.Rol;
import project.cursos.edufree.service.RolService;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {



    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<List<Rol>> obtenerTodos() {
        return ResponseEntity.ok(rolService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerPorId(@PathVariable Integer id) {
        Rol rol = rolService.obtenerPorId(id);
        return ResponseEntity.ok(rol);
    }


    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol) {
        Rol nuevoRol = rolService.crearRol(rol.getNombre());
        return ResponseEntity.ok(nuevoRol);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Integer id) {
        rolService.eliminarRol(id);
        return ResponseEntity.noContent().build();
    }
}
