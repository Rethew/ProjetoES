package pt.estgp.es.projetoes.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import pt.estgp.es.projetoes.spring.domain.Aluno;
import pt.estgp.es.projetoes.spring.domain.Disciplina;
import pt.estgp.es.projetoes.spring.services.AlunoAnotationArgsService;
import pt.estgp.es.projetoes.spring.services.exceptions.EntityNotFoundException;
import pt.estgp.es.projetoes.spring.services.security.AlunoWrapper;
import pt.estgp.es.projetoes.spring.utils.JwtTokenUtil;

@Controller // This means that this class is a Controller
@RequestMapping(path="/api/v1/alunosArgs") // This means URL's start with /demo (after Application path)
public class AlunoControllerAnotationsArgsExamples {


    @Autowired
    AlunoAnotationArgsService alunoService;

    @Autowired
    private JwtTokenUtil jwtTokenUtils;

    static public class AlunoDisciplinaWrapper
    {
        Aluno alunoView;
        Disciplina disciplinaView;

        public Aluno getAlunoView() {
            return alunoView;
        }

        public void setAlunoView(Aluno alunoView) {
            this.alunoView = alunoView;
        }

        public Disciplina getDisciplinaView() {
            return disciplinaView;
        }

        public void setDisciplinaView(Disciplina disciplinaView) {
            this.disciplinaView = disciplinaView;
        }
    }

    @PostMapping(path="/args")
    public @ResponseBody ResponseEntity<Object>
            args(
                @RequestBody AlunoDisciplinaWrapper alunoDisciplinaWrapper
            )
    {
        try {
            alunoService.callAlunoServiceWithArgsInAnnotation(alunoDisciplinaWrapper.disciplinaView.getId(), "asdasdas",alunoDisciplinaWrapper.alunoView.getId());

            alunoService.callAlunoServiceArgsNumbers(alunoDisciplinaWrapper.disciplinaView.getId(), "asdasdas",alunoDisciplinaWrapper.alunoView.getId());

            AlunoWrapper alunoWrapper = new AlunoWrapper();
            alunoWrapper.setAluno(alunoDisciplinaWrapper.getAlunoView());
            alunoService.callAlunoServiceArgsPaths(alunoDisciplinaWrapper.disciplinaView, "asdasdas",alunoWrapper);

            alunoService.callAlunoServiceArgsPathsMaisComlexo(alunoDisciplinaWrapper.disciplinaView, "asdasdas",alunoWrapper);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Autenticação Falhada"
            );
        }
        return ResponseEntity.ok().build();
    }












}


