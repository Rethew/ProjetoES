package pt.estgp.es.projetoes.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pt.estgp.es.projetoes.spring.DAO.UserRepository;
import pt.estgp.es.projetoes.spring.domain.User;

import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path="/api/v1/users") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping(path="/all") // Map ONLY POST Requests
    public @ResponseBody List<User> loadAluno ()
    {
        return (List<User>) userRepository.findAll();
    }

}


