package store.task.repositories;
import org.springframework.stereotype.Repository;
import store.task.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
