package pt.estgp.es.projetoes.spring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.estgp.es.projetoes.spring.domain.Aulas;
import pt.estgp.es.projetoes.spring.services.AulasService;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
public class AulasController {

    private final AulasService aulasService;

    public AulasController(AulasService aulasService) {
        this.aulasService = aulasService;
    }

    @GetMapping
    public ResponseEntity<List<Aulas>> getAll() {
        List<Aulas> all = aulasService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aulas> getById(@PathVariable int id) {
        return aulasService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/disciplina/{disciplinaId}")
    public ResponseEntity<List<Aulas>> getByDisciplinaId(@PathVariable int disciplinaId) {
        List<Aulas> aulas = aulasService.findByDisciplinaId(disciplinaId);
        return ResponseEntity.ok(aulas);
    }

    @PostMapping
    public ResponseEntity<Aulas> create(@RequestBody Aulas aulas) {
        Aulas created = aulasService.create(aulas);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aulas> update(@PathVariable int id, @RequestBody Aulas aulas) {
        Aulas updated = aulasService.update(id, aulas);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        aulasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}