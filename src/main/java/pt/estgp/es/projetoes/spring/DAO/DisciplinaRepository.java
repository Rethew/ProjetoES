package pt.estgp.es.projetoes.spring.DAO;

import org.springframework.data.repository.CrudRepository;
import pt.estgp.es.projetoes.spring.domain.Aluno;
import pt.estgp.es.projetoes.spring.domain.Disciplina;
import pt.estgp.es.projetoes.spring.domain.Disciplina;

public interface DisciplinaRepository extends CrudRepository<Disciplina, Integer> {

}
