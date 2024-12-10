package pt.estgp.es.projetoes.spring.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pt.estgp.es.projetoes.spring.domain.Docente;
import pt.estgp.es.projetoes.spring.domain.User;

import java.util.List;

public interface DocenteRepository extends CrudRepository<Docente, Integer> {



}
