package spotify.Repository;

import spotify.Entity.Role_Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role_Person, Long> {
}
