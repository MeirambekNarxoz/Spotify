package spotify.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spotify.Entity.Role_Person;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role_Person, Long> {
    Optional<Role_Person> findByRole(String role);
}
