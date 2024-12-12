package pt.estgp.es.projetoes.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.estgp.es.projetoes.spring.DAO.AlunoRepository;
import pt.estgp.es.projetoes.spring.domain.Aluno;
import pt.estgp.es.projetoes.spring.domain.Disciplina;
import pt.estgp.es.projetoes.spring.services.exceptions.EntityNotFoundException;
import pt.estgp.es.projetoes.spring.services.security.AlunoWrapper;
import pt.estgp.es.projetoes.spring.services.security.exemplosargs.AnotacaoComArgsUcAluno;
import pt.estgp.es.projetoes.spring.services.security.exemplosargs.AnotacaoComBeanPathParaArgsUcAluno;
import pt.estgp.es.projetoes.spring.services.security.exemplosargs.AnotacaoComBeanPathParaArgsUcAlunoMaisComplexo;
import pt.estgp.es.projetoes.spring.services.security.exemplosargs.AnotacaoSimplesParaObterOsArgsDiretamente;

import java.util.Optional;

@Service

public class AlunoAnotationArgsService {


    @Autowired
    AlunoRepository alunoRepository;

    //https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.htm
    //Necessario colocar na classe de configuracao um @EnableMethodSecurity
    @Transactional
    @AnotacaoSimplesParaObterOsArgsDiretamente
    public Aluno callAlunoServiceWithArgsInAnnotation(Integer ucId, String lixoTeste, Integer alunoId) throws EntityNotFoundException {
        Optional<Aluno> byId = alunoRepository.findById(alunoId);
        if(byId.isEmpty())
            throw new EntityNotFoundException();

        System.out.println("Inside Service callAlunoServiceWithLongs");
        return byId.get();
    }

    @Transactional
    @AnotacaoComArgsUcAluno(parametroComIdAluno = 2,parametroComIdUc = 0)
    public Aluno callAlunoServiceArgsNumbers(Integer ucId, String lixoTeste, Integer alunoId) throws EntityNotFoundException {
        Optional<Aluno> byId = alunoRepository.findById(alunoId);
        if(byId.isEmpty())
            throw new EntityNotFoundException();

        System.out.println("Inside Service callAlunoServiceWithLongs");
        return byId.get();
    }

    @Transactional
    @AnotacaoComBeanPathParaArgsUcAluno(argAluno = 2,argUc = 0, pathParaParametroComIdAluno = "aluno.id", pathParaParametroComIdUc = "id")
    public Aluno callAlunoServiceArgsPaths(Disciplina uc, String lixoTeste, AlunoWrapper alunoWrapper) throws EntityNotFoundException {
        Optional<Aluno> byId = alunoRepository.findById(alunoWrapper.getAluno().getId());
        if(byId.isEmpty())
            throw new EntityNotFoundException();

        System.out.println("callAlunoServiceArgsPaths");
        return byId.get();
    }

    @Transactional
    @AnotacaoComBeanPathParaArgsUcAlunoMaisComplexo(pathParaParametroComIdAluno = "2.aluno.id", pathParaParametroComIdUc = "0.id")
    public Aluno callAlunoServiceArgsPathsMaisComlexo(Disciplina uc, String lixoTeste, AlunoWrapper alunoWrapper) throws EntityNotFoundException {
        Optional<Aluno> byId = alunoRepository.findById(alunoWrapper.getAluno().getId());
        if(byId.isEmpty())
            throw new EntityNotFoundException();

        System.out.println("callAlunoServiceArgsPathsMaisComlexo");
        return byId.get();
    }


}
