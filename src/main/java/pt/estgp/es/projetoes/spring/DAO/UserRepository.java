package pt.estgp.es.projetoes.spring.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pt.estgp.es.projetoes.spring.domain.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer>
{

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String emailAddress);

    @Query("SELECT u FROM User u WHERE u.email = ?1 and u.password = ?2")
    List<User> findByUsernameAndPassword(String email, String password);


}