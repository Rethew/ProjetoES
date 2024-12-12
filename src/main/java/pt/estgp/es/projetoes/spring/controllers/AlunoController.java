package pt.estgp.es.projetoes.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pt.estgp.es.projetoes.spring.domain.Aluno;
import pt.estgp.es.projetoes.spring.services.AlunoService;
import pt.estgp.es.projetoes.spring.services.exceptions.EntityNotFoundException;
import pt.estgp.es.projetoes.spring.utils.JwtTokenUtil;

@Controller // This means that this class is a Controller
@RequestMapping(path="/api/v1/alunos") // This means URL's start with /demo (after Application path)
public class AlunoController {


    @Autowired
    AlunoService alunoService;


    @GetMapping(path="/{id}") // Map ONLY POST Requests
    public @ResponseBody Aluno loadAluno (@PathVariable("id") Integer id)
    {
        try {
            return alunoService.load(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não encontrado"
            );
        }
    }

    @Autowired
    private JwtTokenUtil jwtTokenUtils;

    @PostMapping(path="") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<Object> salvar(@RequestBody Aluno view)
    {
        try {
            alunoService.saveAluno(view);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Autenticação Falhada"
            );
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(path="") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<Aluno> novo(@RequestBody Aluno view)
    {
        Aluno aluno = alunoService.newAluno(view);
        return ResponseEntity.ok(aluno);
    }
}


