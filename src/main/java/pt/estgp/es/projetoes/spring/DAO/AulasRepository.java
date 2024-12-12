package pt.estgp.es.projetoes.spring.DAO;

import org.springframework.data.repository.CrudRepository;
import pt.estgp.es.projetoes.spring.domain.Aulas;
import pt.estgp.es.projetoes.spring.domain.Docente;

public interface AulasRepository extends CrudRepository<Aulas, Integer> {
}
