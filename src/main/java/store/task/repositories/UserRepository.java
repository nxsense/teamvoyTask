package store.task.repositories;


import org.springframework.stereotype.Repository;
import store.task.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

}
