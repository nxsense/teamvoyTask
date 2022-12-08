package store.task.services;

import store.task.dto.GoodsDTO;
import store.task.dto.ShoppingCartDTO;
import store.task.entities.Good;
import store.task.entities.User;

public interface ShoppingCartService {
    ShoppingCartDTO findByUser(User user);
    void addGoods(User user, Good good, int quantity);
    ShoppingCartDTO initializeShoppingCart(User user);
    void clear();
}
