package project.cursos.edufree.service;

import org.springframework.stereotype.Service;
import project.cursos.edufree.exception.ConflictException;
import project.cursos.edufree.exception.ResourceNotFoundException;
import project.cursos.edufree.model.Rol;
import project.cursos.edufree.model.Usuario;
import project.cursos.edufree.repository.RolRepository;
import project.cursos.edufree.repository.UsuarioRepository;
import project.cursos.edufree.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    private final RolRepository rolRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }


    public Optional<Usuario> obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario obtenerPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + id + " no encontrado"));
    }

    public Usuario CrearUsuario(String nombre, String email, String password, String telefono, Integer rolId) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new ConflictException("El email ya está en uso.");
        }

        if (usuarioRepository.existsByTelefono(telefono)) {
            throw new ConflictException("El número de teléfono ya está en uso.");
        }

        Rol rol = rolRepository.findById(rolId)
                .orElseThrow(() -> new ResourceNotFoundException("Rol con ID " + rolId + " no encontrado"));

        Usuario usuario = new Usuario(nombre, email, password, telefono, rol);
        return usuarioRepository.save(usuario);
    }


    public Usuario actualizarUsuario(Integer id, String nombre, String email, String password, String telefono, Integer rolId) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + id + " no encontrado"));

        Rol rol = rolRepository.findById(rolId)
                .orElseThrow(() -> new ResourceNotFoundException("Rol con ID " + rolId + " no encontrado"));

        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setTelefono(telefono);
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarParcial(Integer id, Usuario usuarioParcial) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + id + " no encontrado"));

        if (usuarioParcial.getNombre() != null) usuario.setNombre(usuarioParcial.getNombre());
        if (usuarioParcial.getEmail() != null) usuario.setEmail(usuarioParcial.getEmail());
        if (usuarioParcial.getTelefono() != null) usuario.setTelefono(usuarioParcial.getTelefono());
        if (usuarioParcial.getPassword() != null) usuario.setPassword(usuarioParcial.getPassword());

        if (usuarioParcial.getRol() != null && usuarioParcial.getRol().getId() != null) {
            Integer rolId = usuarioParcial.getRol().getId();
            Rol rol = rolRepository.findById(rolId)
                    .orElseThrow(() -> new ResourceNotFoundException("Rol con ID " + rolId + " no encontrado"));
            usuario.setRol(rol);
        }

        return usuarioRepository.save(usuario);
    }


    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public List<UsuarioDTO> obtenerTodosComoDTO() {
        return usuarioRepository.findAll().stream().map(usuario -> {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setId(usuario.getId());
            dto.setNombre(usuario.getNombre());
            dto.setEmail(usuario.getEmail());
            dto.setRol(usuario.getRol());

            return dto;
        }).toList();
    }



}
