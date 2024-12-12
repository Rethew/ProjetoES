package pt.estgp.es.projetoes.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pt.estgp.es.projetoes.spring.services.ServiceExample;


@Controller
public class GreetingController {

    private final ServiceExample service;

    public GreetingController(ServiceExample service) {
        this.service = service;
    }

    @RequestMapping("/greeting")
    public @ResponseBody String greeting() {
        return service.greet();
    }

}