package pt.estgp.es.projetoes.spring.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disciplina_seq")
    @SequenceGenerator(name = "disciplina_seq", sequenceName = "disciplina_sequence", allocationSize = 1, initialValue = 0)
    private Integer id;

    String nome;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "disciplina_aluno",
            joinColumns = @JoinColumn(name = "id_disciplina", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_aluno", referencedColumnName = "id"))
    private Set<Aluno> alunos;

    @OneToOne
    private Docente docente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }
}
