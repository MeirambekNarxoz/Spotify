package spotify.Repository;

import org.springframework.stereotype.Repository;
import spotify.Entity.Role_Person;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface RoleRepository extends JpaRepository<Role_Person, Long> {
}
