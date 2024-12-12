package pt.estgp.es.projetoes.spring.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.estgp.es.projetoes.spring.DAO.UserRepository;
import pt.estgp.es.projetoes.spring.domain.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        pt.estgp.es.projetoes.spring.domain.User person = userRepo.findByEmail(username);

        if(person == null)
                throw new UsernameNotFoundException("User not found with username: " + username);

        return new User(username, person.getPassword(),true,true,true,true,getGrantedAuthorities(person.getRoles()));
    }



    private List<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}