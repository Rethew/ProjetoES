package pt.estgp.es.projetoes.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.estgp.es.projetoes.spring.DAO.AlunoRepository;
import pt.estgp.es.projetoes.spring.DAO.DisciplinaRepository;
import pt.estgp.es.projetoes.spring.domain.Aluno;
import pt.estgp.es.projetoes.spring.domain.Disciplina;

@Service
public class ServiceExample {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;
    /**
     *
     */
    @Transactional
    public void carregaDisciplinasAluno() {
        //load Aluno
        Iterable<Disciplina> all = disciplinaRepository.findAll();
        Iterable<Aluno> alunos = alunoRepository.findAll();
        alunos.forEach(aluno->{
            all.forEach(d-> aluno.getDisciplinas().add(d));
            alunoRepository.save(aluno);
        });
    }

    public String greet() {
        return "Hello, World";
    }

}
