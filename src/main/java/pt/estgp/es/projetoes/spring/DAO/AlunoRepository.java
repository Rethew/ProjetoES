package pt.estgp.es.projetoes.spring.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pt.estgp.es.projetoes.spring.domain.Aluno;
import pt.estgp.es.projetoes.spring.domain.User;

import java.util.List;

public interface AlunoRepository extends CrudRepository<Aluno, Integer>
{


}
