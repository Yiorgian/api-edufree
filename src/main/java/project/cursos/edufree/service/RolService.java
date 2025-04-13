package project.cursos.edufree.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.cursos.edufree.exception.ResourceNotFoundException;
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

    public Rol obtenerPorId(Integer id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol con ID " + id + " no encontrado"));
    }


    public Rol crearRol(String nombre) {
        if (rolRepository.findByNombre(nombre).isPresent()) {
            throw new RuntimeException("El rol ya existe");
        }
        Rol rol = new Rol(nombre);
        return rolRepository.save(rol);
    }


    public void eliminarRol(Integer id) {
        rolRepository.deleteById(id);
    }
}
