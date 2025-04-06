package project.cursos.edufree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.cursos.edufree.dto.Crear_Datos.CrearNotificacionDTO;
import project.cursos.edufree.dto.NotificacionDTO;
import project.cursos.edufree.model.Notificacion;
import project.cursos.edufree.service.NotificacionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public ResponseEntity<List<Notificacion>> obtenerTodas() {
        return ResponseEntity.ok(notificacionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerPorId(@PathVariable Integer id) {
        Notificacion notificacion = notificacionService.obtenerPorId(id);
        return ResponseEntity.ok(notificacion);
    }


    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Notificacion>> obtenerPorUsuario(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(notificacionService.obtenerPorUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<Notificacion> crearNotificacion(@RequestBody CrearNotificacionDTO dto) {
        Notificacion notificacion = notificacionService.crearNotificacion(dto.getUsuarioId(), dto.getMensaje());
        return ResponseEntity.ok(notificacion);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> actualizarNotificacion(
            @PathVariable Integer id,
            @RequestBody CrearNotificacionDTO dto) {
        Notificacion notificacion = notificacionService.actualizarNotificacion(id, dto);
        return ResponseEntity.ok(notificacion);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Notificacion> actualizarParcial(
            @PathVariable Integer id,
            @RequestBody CrearNotificacionDTO dto) {
        Notificacion notificacion = notificacionService.actualizarParcial(id, dto);
        return ResponseEntity.ok(notificacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable Integer id) {
        notificacionService.eliminarNotificacion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dto")
    public ResponseEntity<List<NotificacionDTO>> obtenerTodasDTO() {
        return ResponseEntity.ok(notificacionService.obtenerTodasComoDTO());}
}
