package store.task.repositories;

import org.springframework.stereotype.Repository;
import store.task.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import store.task.entities.User;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    ShoppingCart findByUser(User user);
}
