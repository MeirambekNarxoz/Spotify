package book.Repository;

import book.Entity.Role_Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role_Person, Long> {
}
