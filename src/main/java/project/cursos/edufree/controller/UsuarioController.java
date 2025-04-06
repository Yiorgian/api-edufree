package project.cursos.edufree.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.cursos.edufree.dto.Crear_Datos.CrearUsuarioDTO;
import project.cursos.edufree.model.Usuario;
import project.cursos.edufree.service.UsuarioService;
import project.cursos.edufree.dto.UsuarioDTO;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(usuario);
    }


    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody CrearUsuarioDTO dto) {
        Usuario usuario = usuarioService.crearUsuario(
                dto.getNombre(), dto.getEmail(), dto.getPassword(), dto.getTelefono(), dto.getRolId()
        );
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Integer id,
            @RequestBody CrearUsuarioDTO dto) {

        Usuario usuario = usuarioService.actualizarUsuario(
                id,
                dto.getNombre(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getTelefono(),
                dto.getRolId()
        );
        return ResponseEntity.ok(usuario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> actualizarParcialUsuario(
            @PathVariable Integer id,
            @RequestBody Usuario usuarioParcial) {

        Usuario usuarioActualizado = usuarioService.actualizarParcial(id, usuarioParcial);
        return ResponseEntity.ok(usuarioActualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/dto")
    public ResponseEntity<List<UsuarioDTO>> obtenerTodosDTO() {
        return ResponseEntity.ok(usuarioService.obtenerTodosComoDTO());
    }
}
