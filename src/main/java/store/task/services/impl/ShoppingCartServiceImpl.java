package store.task.services.impl;

import store.task.dto.ShoppingCartDTO;
import store.task.entities.Good;
import store.task.entities.User;
import store.task.services.ShoppingCartService;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Override
    public ShoppingCartDTO findByUser(User user) {
        return null;
    }

    @Override
    public void addGoods(User user, Good good, int quantity) {

    }

    @Override
    public ShoppingCartDTO initializeShoppingCart(User user) {
        return null;
    }

    @Override
    public void clear() {

    }
}
