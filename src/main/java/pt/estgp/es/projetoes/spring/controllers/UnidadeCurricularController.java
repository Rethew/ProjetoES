package pt.estgp.es.projetoes.spring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.estgp.es.projetoes.spring.domain.Disciplina;
import pt.estgp.es.projetoes.spring.domain.Docente;
import pt.estgp.es.projetoes.spring.services.UnidadeCurricularService;

import java.util.List;

@RestController
@RequestMapping("/api/unidadecurricular")
public class UnidadeCurricularController {

    private final UnidadeCurricularService unidadeCurricularService;

    public UnidadeCurricularController(UnidadeCurricularService unidadeCurricularService) {
        this.unidadeCurricularService = unidadeCurricularService;
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> getAll() {
        List<Disciplina> all = unidadeCurricularService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> getById(@PathVariable int id) {
        return unidadeCurricularService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Disciplina> create(@RequestBody DisciplinaRequest request) {
        Disciplina created = unidadeCurricularService.create(
                request.getDisciplinaNome(),
                request.getDocente());
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable int id, @RequestBody DisciplinaRequest request) {
        Disciplina updated = unidadeCurricularService.update(
                id,
                request.getDisciplinaNome(),
                request.getDocente());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        unidadeCurricularService.delete(id);
        return ResponseEntity.noContent().build();
    }

    public static class DisciplinaRequest {
        private String disciplinaNome;
        private Docente docente;

        public String getDisciplinaNome() {
            return disciplinaNome;
        }

        public void setDisciplinaNome(String disciplinaNome) {
            this.disciplinaNome = disciplinaNome;
        }

        public Docente getDocente() {
            return docente;
        }

        public void setDocente(Docente docente) {
            this.docente = docente;
        }
    }
}