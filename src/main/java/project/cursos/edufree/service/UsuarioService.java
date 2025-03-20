package project.cursos.edufree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.cursos.edufree.model.Rol;
import project.cursos.edufree.model.Usuario;
import project.cursos.edufree.repository.RolRepository;
import project.cursos.edufree.repository.UsuarioRepository;

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

    public Optional<Usuario> obtenerPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario crearUsuario(String nombre, String email, String password, Integer rolId) {
        Optional<Rol> rolOpt = rolRepository.findById(rolId);
        if (rolOpt.isPresent()) {
            Usuario usuario = new Usuario(nombre, email, password, rolOpt.get());
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Rol no encontrado");
        }
    }


    public Usuario actualizarUsuario(Integer id, String nombre, String email, String password, Integer rolId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        Optional<Rol> rolOpt = rolRepository.findById(rolId);

        if (usuarioOpt.isPresent() && rolOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setRol(rolOpt.get());
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuario o Rol no encontrado");
        }
    }

    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
