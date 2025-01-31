package pt.estgp.es.projetoes.spring.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.estgp.es.projetoes.spring.DAO.*;
import pt.estgp.es.projetoes.spring.domain.*;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeCurricularService {

    private final DisciplinaRepository disciplinaRepository;
    private final DocenteRepository docenteRepository;

    public UnidadeCurricularService(DisciplinaRepository disciplinaRepository, DocenteRepository docenteRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.docenteRepository = docenteRepository;
    }

    @Transactional(readOnly = true)
    public List<Disciplina> findAll() {
        return (List<Disciplina>) disciplinaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Disciplina> findById(int id) {
        return disciplinaRepository.findById(id);
    }

    @Transactional
    public Disciplina create(String disciplinaNome, Docente docente) {
        if (docente == null || docente.getId() == null) {
            throw new IllegalArgumentException("Docente is required.");
        }

        Docente docenteUC = docenteRepository.findById(docente.getId())
                .orElseThrow(() -> new IllegalArgumentException("Docente not found with id " + docente.getId()));

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(disciplinaNome);
        disciplina.setDocente(docenteUC);

        return disciplinaRepository.save(disciplina);
    }

    @Transactional
    public Disciplina update(int disciplinaId, String disciplinaNome, Docente docente) {
        Disciplina existing = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina not found with id " + disciplinaId));

        if (docente != null && docente.getId() != null) {
            Docente docenteUC = docenteRepository.findById(docente.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Docente not found with id " + docente.getId()));
            existing.setDocente(docenteUC);
        }

        if (disciplinaNome != null) {
            existing.setNome(disciplinaNome);
        }

        return disciplinaRepository.save(existing);
    }

    @Transactional
    public void delete(int id) {
        if (!disciplinaRepository.existsById(id)) {
            throw new IllegalArgumentException("Disciplina not found with id " + id);
        }
        disciplinaRepository.deleteById(id);
    }
}