package store.task.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import store.task.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import store.task.entities.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select distinct o from Order o "
            + "left join fetch o.goods "
            + "left join fetch o.user where o.user =:user")
    List<Order> findAllByUser(User user);
}
