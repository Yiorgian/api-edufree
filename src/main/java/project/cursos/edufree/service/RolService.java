package project.cursos.edufree.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.cursos.edufree.model.Rol;
import project.cursos.edufree.repository.RolRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }

    public Optional<Rol> obtenerPorId(Integer id) {
        return rolRepository.findById(id);
    }

    public Optional<Rol> obtenerPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre);
    }

    public Rol crearRol(String nombre) {
        if (rolRepository.findByNombre(nombre).isPresent()) {
            throw new RuntimeException("El rol ya existe");
        }
        Rol rol = new Rol(nombre);
        return rolRepository.save(rol);
    }

    public Rol actualizarRol(Integer id, String nombre) {
        Optional<Rol> rolOpt = rolRepository.findById(id);
        if (rolOpt.isPresent()) {
            Rol rol = rolOpt.get();
            rol.setNombre(nombre);
            return rolRepository.save(rol);
        } else {
            throw new RuntimeException("Rol no encontrado");
        }
    }

    public void eliminarRol(Integer id) {
        rolRepository.deleteById(id);
    }
}
