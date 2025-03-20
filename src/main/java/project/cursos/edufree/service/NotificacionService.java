package project.cursos.edufree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.cursos.edufree.model.Notificacion;
import project.cursos.edufree.model.Usuario;
import project.cursos.edufree.repository.NotificacionRepository;
import project.cursos.edufree.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;


    private final UsuarioRepository usuarioRepository;

    public NotificacionService(NotificacionRepository notificacionRepository, UsuarioRepository usuarioRepository) {
        this.notificacionRepository = notificacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Notificacion> obtenerTodas() {
        return notificacionRepository.findAll();
    }


    public Optional<Notificacion> obtenerPorId(Integer id) {
        return notificacionRepository.findById(id);
    }

    public List<Notificacion> obtenerPorUsuario(Integer usuarioId) {
        return notificacionRepository.findByUsuarioId(usuarioId);
    }

    public Notificacion crearNotificacion(Integer usuarioId, String mensaje) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            Notificacion notificacion = new Notificacion(usuarioOpt.get(), mensaje);
            return notificacionRepository.save(notificacion);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    public void eliminarNotificacion(Integer id) {
        notificacionRepository.deleteById(id);
    }
}
