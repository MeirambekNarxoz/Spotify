package spotify.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spotify.Dto.CustomUserDetails;
import spotify.Entity.Person;
import spotify.Repository.PersonRepository;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    public CustomUserDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(username);

        return new CustomUserDetails(
                person.getId(),
                person.getEmail(),
                person.getPassword(),
                Collections.emptyList() // Укажите роли и права доступа, если нужно
        );
    }
}
