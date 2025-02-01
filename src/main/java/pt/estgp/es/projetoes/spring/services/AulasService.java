package pt.estgp.es.projetoes.spring.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.estgp.es.projetoes.spring.DAO.AulasRepository;
import pt.estgp.es.projetoes.spring.domain.Aulas;

import java.util.List;
import java.util.Optional;

@Service
public class AulasService {

    private final AulasRepository aulasRepository;

    public AulasService(AulasRepository aulasRepository) {
        this.aulasRepository = aulasRepository;
    }

    @Transactional(readOnly = true)
    public List<Aulas> findAll() {
        return (List<Aulas>) aulasRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Aulas> findById(int id) {
        return aulasRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Aulas> findByDisciplinaId(int disciplinaId) {
        return aulasRepository.findByDisciplinaId(disciplinaId);
    }

    @Transactional
    public Aulas create(Aulas aula) {
        return aulasRepository.save(aula);
    }

    @Transactional
    public Aulas update(int id, Aulas aula) {
        Aulas existingAula = aulasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aula not found with id " + id));

        return aulasRepository.save(existingAula);
    }

    @Transactional
    public void delete(int id) {
        if (!aulasRepository.existsById(id)) {
            throw new IllegalArgumentException("Aula not found with id " + id);
        }
        aulasRepository.deleteById(id);
    }
}
