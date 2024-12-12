package pt.estgp.es.projetoes.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.estgp.es.projetoes.spring.DAO.UserRepository;
import pt.estgp.es.projetoes.spring.domain.Aluno;

@Controller // This means that this class is a Controller
@RequestMapping(path="/api/v1/hello") // This means URL's start with /demo (after Application path)
public class HelloController {

    @Autowired
    UserRepository userRepository;


    @GetMapping(path="") // Map ONLY POST Requests
    public @ResponseBody String loadAluno ()
    {
        return "Hello world";
    }


    @PostMapping(path="/echoAluno") // Map ONLY POST Requests
    public @ResponseBody Aluno echoAluno(@RequestBody Aluno a)
    {

        System.out.println("a.toString() = " + a.toString());

        return a;
    }







}


