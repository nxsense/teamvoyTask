package store.task.services;

import store.task.dto.OrderDTO;
import store.task.dto.ShoppingCartDTO;

import java.util.List;

public interface OrdersService {
    List<OrderDTO> findAllByUserId(int id);
    OrderDTO resolveOrder(ShoppingCartDTO shoppingCartDTO);
}
