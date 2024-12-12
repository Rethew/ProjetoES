package pt.estgp.es.projetoes.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.estgp.es.projetoes.spring.DAO.AlunoRepository;
import pt.estgp.es.projetoes.spring.domain.Aluno;
import pt.estgp.es.projetoes.spring.services.exceptions.EntityNotFoundException;
import pt.estgp.es.projetoes.spring.services.security.CustomSecurityAnnotation;

import java.util.Optional;

@Service

public class AlunoService {


    @Autowired
    AlunoRepository alunoRepository;

    //https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.htm
    //Necessario colocar na classe de configuracao um @EnableMethodSecurity
    @Transactional
    //@Secured({ "ROLE_ADMIN" })
    //@PreFilter()
    //@PostFilter()
    //@PreAuthorize("hasAuthority('permission:read')")
    //@PostAuthorize("returnObject.owner == authentication.name")
    //@PreAuthorize("hasAuthority('db') and hasRole('ADMIN')")
    @CustomSecurityAnnotation
    @PreAuthorize("hasRole('ADMIN')")
    @PostAuthorize("returnObject.id == 2")

    //@PreAuthorize("#view.id == authentication.name")
    public Aluno saveAluno(@Param("view") Aluno view) throws EntityNotFoundException {
        view.setNome("nome recebido: " + view.getNome());

        Optional<Aluno> byId = alunoRepository.findById(view.getId());
        if(byId.isEmpty())
            throw new EntityNotFoundException();
        Aluno aluno = byId.get();
        aluno.setNome(view.getNome());
        alunoRepository.save(aluno);
        return view;
    }

    public Aluno load(Integer id) throws EntityNotFoundException {

        Optional<Aluno> byId = alunoRepository.findById(id);
        if(byId.isEmpty())
            throw new EntityNotFoundException();
        return byId.get();
    }


    public Aluno newAluno(Aluno view) {
        Aluno save = alunoRepository.save(view);
        return save;
    }
}
