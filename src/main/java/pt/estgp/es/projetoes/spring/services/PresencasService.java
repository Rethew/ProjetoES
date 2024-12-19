package pt.estgp.es.projetoes.spring.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.estgp.es.projetoes.spring.domain.Aluno;
import pt.estgp.es.projetoes.spring.domain.Aulas;
import pt.estgp.es.projetoes.spring.domain.Presencas;
import pt.estgp.es.projetoes.spring.DAO.AlunoRepository;
import pt.estgp.es.projetoes.spring.DAO.AulasRepository;
import pt.estgp.es.projetoes.spring.DAO.PresencasRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PresencasService {

    private final PresencasRepository presencasRepository;
    private final AlunoRepository alunoRepository;
    private final AulasRepository aulasRepository;

    public PresencasService(PresencasRepository presencasRepository,
                            AlunoRepository alunoRepository,
                            AulasRepository aulasRepository) {
        this.presencasRepository = presencasRepository;
        this.alunoRepository = alunoRepository;
        this.aulasRepository = aulasRepository;
    }

    @Transactional(readOnly = true)
    public List<Presencas> findAll() {
        return presencasRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Presencas> findById(int id) {
        return presencasRepository.findById(id);
    }

    @Transactional
    public Presencas create(int alunoId, int aulaId, String estado, String data) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno not found with id " + alunoId));
        Aulas aula = aulasRepository.findById(aulaId)
                .orElseThrow(() -> new IllegalArgumentException("Aula not found with id " + aulaId));

        Presencas presenca = new Presencas();
        presenca.setId_aluno(aluno);
        presenca.setId_aula(aula);
        presenca.setEstado(estado);
        presenca.setData(data);

        return presencasRepository.save(presenca);
    }

    @Transactional
    public Presencas update(int id, int alunoId, int aulaId, String estado, String data) {
        Presencas existing = presencasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Presenca not found with id " + id));

        if (alunoId != 0) {
            Aluno aluno = alunoRepository.findById(alunoId)
                    .orElseThrow(() -> new IllegalArgumentException("Aluno not found with id " + alunoId));
            existing.setId_aluno(aluno);
        }

        if (aulaId != 0) {
            Aulas aula = aulasRepository.findById(aulaId)
                    .orElseThrow(() -> new IllegalArgumentException("Aula not found with id " + aulaId));
            existing.setId_aula(aula);
        }

        if (estado != null) {
            existing.setEstado(estado);
        }

        if (data != null) {
            existing.setData(data);
        }

        return presencasRepository.save(existing);
    }

    @Transactional
    public void delete(int id) {
        if (!presencasRepository.existsById(id)) {
            throw new IllegalArgumentException("Presenca not found with id " + id);
        }
        presencasRepository.deleteById(id);
    }
}
