package project.cursos.edufree.service;

import org.springframework.stereotype.Service;
import project.cursos.edufree.dto.Crear_Datos.CrearNotificacionDTO;
import project.cursos.edufree.exception.ResourceNotFoundException;
import project.cursos.edufree.model.Notificacion;
import project.cursos.edufree.model.Usuario;
import project.cursos.edufree.repository.NotificacionRepository;
import project.cursos.edufree.repository.UsuarioRepository;
import project.cursos.edufree.dto.NotificacionDTO;
import project.cursos.edufree.dto.UsuarioDTO;

import java.util.List;

@Service
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;


    private final UsuarioRepository usuarioRepository;

    public List<NotificacionDTO> obtenerTodasComoDTO() {
        return notificacionRepository.findAll().stream().map(noti -> {
            NotificacionDTO dto = new NotificacionDTO();
            dto.setId(noti.getId());
            dto.setMensaje(noti.getMensaje());
            dto.setFechaEnvio(noti.getFechaEnvio());

            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(noti.getUsuario().getId());
            usuarioDTO.setNombre(noti.getUsuario().getNombre());
            usuarioDTO.setEmail(noti.getUsuario().getEmail());

            usuarioDTO.setRol(noti.getUsuario().getRol());
            dto.setUsuario(usuarioDTO);

            return dto;
        }).toList();
    }


    public Notificacion obtenerPorId(Integer id) {
        return notificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificación con ID " + id + " no encontrada"));
    }


    public NotificacionService(NotificacionRepository notificacionRepository, UsuarioRepository usuarioRepository) {
        this.notificacionRepository = notificacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Notificacion> obtenerTodas() {
        return notificacionRepository.findAll();
    }


    public List<Notificacion> obtenerPorUsuario(Integer usuarioId) {
        return notificacionRepository.findByUsuarioId(usuarioId);
    }

    public Notificacion crearNotificacion(Integer usuarioId, String mensaje) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + usuarioId + " no encontrado"));

        return notificacionRepository.save(new Notificacion(usuario, mensaje));
    }

    public Notificacion actualizarNotificacion(Integer id, CrearNotificacionDTO dto) {
        Notificacion notificacion = obtenerPorId(id);
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + dto.getUsuarioId() + " no encontrado"));

        notificacion.setMensaje(dto.getMensaje());
        notificacion.setUsuario(usuario);

        return notificacionRepository.save(notificacion);
    }

    public Notificacion actualizarParcial(Integer id, CrearNotificacionDTO dto) {
        Notificacion notificacion = obtenerPorId(id);

        if (dto.getMensaje() != null) {
            notificacion.setMensaje(dto.getMensaje());
        }

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + dto.getUsuarioId() + " no encontrado"));
            notificacion.setUsuario(usuario);
        }

        return notificacionRepository.save(notificacion);
    }


    public void eliminarNotificacion(Integer id) {
        notificacionRepository.deleteById(id);
    }




}
