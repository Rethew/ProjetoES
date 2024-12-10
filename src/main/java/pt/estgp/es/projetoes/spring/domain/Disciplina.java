package pt.estgp.es.projetoes.spring.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    String nome;

    @ManyToMany
    @JoinTable(
            name = "disciplina_aluno",
            joinColumns = @JoinColumn(name = "id_disciplina", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_aluno", referencedColumnName = "id"))
    private Set<Aluno> alunos;

    @OneToMany
    private Set<Aulas> aulas;

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

    public Set<Aulas> getAulas() {
        return aulas;
    }

    public void setAulas(Set<Aulas> aulas) {
        this.aulas = aulas;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }
}
