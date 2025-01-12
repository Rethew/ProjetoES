package pt.estgp.es.projetoes.spring.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "docente_seq")
    @SequenceGenerator(name = "docente_seq", sequenceName = "docente_sequence", allocationSize = 1, initialValue = 0)
    private Integer id;

    private  String nome;

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
}

