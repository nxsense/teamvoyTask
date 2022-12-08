package store.task.repositories;

import org.springframework.stereotype.Repository;
import store.task.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

}
