package pt.estgp.es.projetoes.spring.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Aulas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Disciplina disciplina;

    @OneToMany
    private Set<Presencas> presencas;

    private String data;
    private String hora;
    private String duracao;
    private String estado;

    public int getId() {
        return id;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public String getDuracao() {
        return duracao;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
