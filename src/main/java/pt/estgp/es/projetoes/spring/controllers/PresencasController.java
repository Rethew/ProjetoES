package pt.estgp.es.projetoes.spring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.estgp.es.projetoes.spring.domain.Presencas;
import pt.estgp.es.projetoes.spring.services.PresencasService;

import java.util.List;

@RestController
@RequestMapping("/api/presencas")
public class PresencasController {

    private final PresencasService presencasService;

    public PresencasController(PresencasService presencasService) {
        this.presencasService = presencasService;
    }

    @GetMapping
    public ResponseEntity<List<Presencas>> getAll() {
        List<Presencas> all = presencasService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Presencas> getById(@PathVariable int id) {
        return presencasService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Example JSON to create:
     * {
     *   "alunoId": 1,
     *   "aulaId": 2,
     *   "estado": "Presente",
     *   "data": "2024-01-10"
     * }
     */
    @PostMapping
    public ResponseEntity<Presencas> create(@RequestBody PresencaRequest request) {
        Presencas created = presencasService.create(
                request.getAlunoId(),
                request.getAulaId(),
                request.getEstado(),
                request.getData());
        return ResponseEntity.ok(created);
    }

    /**
     * Example JSON to update:
     * {
     *   "alunoId": 1,
     *   "aulaId": 3,
     *   "estado": "Ausente",
     *   "data": "2024-01-11"
     * }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Presencas> update(@PathVariable int id, @RequestBody PresencaRequest request) {
        Presencas updated = presencasService.update(
                id,
                request.getAlunoId(),
                request.getAulaId(),
                request.getEstado(),
                request.getData());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        presencasService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Simple DTO for request data
    public static class PresencaRequest {
        private int alunoId;
        private int aulaId;
        private String estado;
        private String data;

        public int getAlunoId() {
            return alunoId;
        }

        public void setAlunoId(int alunoId) {
            this.alunoId = alunoId;
        }

        public int getAulaId() {
            return aulaId;
        }

        public void setAulaId(int aulaId) {
            this.aulaId = aulaId;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
