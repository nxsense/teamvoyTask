package store.task.services.impl;

import org.springframework.stereotype.Service;
import store.task.dto.ShoppingCartDTO;
import store.task.entities.Good;
import store.task.entities.ShoppingCart;
import store.task.entities.User;
import store.task.mappers.ShoppingCartMapper;
import store.task.repositories.ShoppingCartRepository;
import store.task.services.ShoppingCartService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper mapper;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ShoppingCartMapper mapper) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.mapper = mapper;
    }

    @Override
    public ShoppingCartDTO findByUser(User user) {
        return mapper.toDto(shoppingCartRepository.findByUser(user));
    }

    @Override
    public void addGoods(User user, Good good, int quantity) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user);
        List<Good> goodList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            goodList.add(good);
        }
        shoppingCart.getGoods().addAll(goodList);
    }

    @Override
    public ShoppingCartDTO initializeShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return mapper.toDto(shoppingCart);
    }

    @Override
    public void clear(int id) {
        if (shoppingCartRepository.findById(id).isPresent()) {
            ShoppingCart shoppingCart = shoppingCartRepository.findById(id).get();
            shoppingCart.getGoods().removeAll(shoppingCart.getGoods());
            shoppingCartRepository.save(shoppingCart);
        }
    }
}
