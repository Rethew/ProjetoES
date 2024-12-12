package pt.estgp.es.projetoes.spring.domain;

import jakarta.persistence.*;

@Entity
public class Presencas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Aulas aula;

    private String estado;
    private String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aluno getId_aluno() {
        return aluno;
    }

    public void setId_aluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Aulas getId_aula() {
        return aula;
    }

    public void setId_aula(Aulas aula) {
        this.aula = aula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
