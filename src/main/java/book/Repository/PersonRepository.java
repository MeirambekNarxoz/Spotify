package book.Repository;

import book.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByEmail(String email);
    Person findByEmail(String email);
}
