package project.cursos.edufree.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "temas")
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    @JsonBackReference(value = "curso-temas")
    private Curso curso;

    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "tema-subtemas")
    private List<Subtema> subtemas;

    public Tema() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Subtema> getSubtemas() {
        return subtemas;
    }

    public void setSubtemas(List<Subtema> subtemas) {
        this.subtemas = subtemas;
    }
}
