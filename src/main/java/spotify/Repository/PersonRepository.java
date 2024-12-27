package spotify.Repository;

import org.springframework.stereotype.Repository;
import spotify.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByEmail(String email);
    Person findByEmail(String email);
}
