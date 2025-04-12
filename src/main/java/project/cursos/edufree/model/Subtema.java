package project.cursos.edufree.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "subtemas")
public class Subtema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer tiempo;

    @ManyToOne
    @JoinColumn(name = "tema_id", nullable = false)
    @JsonBackReference(value = "tema-subtemas")
    private Tema tema;
    

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

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
}
