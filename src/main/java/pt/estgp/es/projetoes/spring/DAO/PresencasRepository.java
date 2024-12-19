package pt.estgp.es.projetoes.spring.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.estgp.es.projetoes.spring.domain.Presencas;

public interface PresencasRepository extends JpaRepository<Presencas, Integer> {

}
