package store.task.repositories;

import org.springframework.stereotype.Repository;
import store.task.entities.Good;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GoodsRepository extends JpaRepository<Good, Integer> {

}
