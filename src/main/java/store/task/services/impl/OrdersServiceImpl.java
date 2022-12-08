package store.task.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.task.dto.OrderDTO;
import store.task.dto.ShoppingCartDTO;
import store.task.mappers.OrderMapper;
import store.task.repositories.OrderRepository;
import store.task.repositories.UserRepository;
import store.task.services.OrdersService;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;

    @Autowired
    public OrdersServiceImpl(UserRepository userRepository, OrderRepository orderRepository, OrderMapper mapper) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    public List<OrderDTO> findAllByUserId(int id) {
        return ;
    }

    @Override
    public OrderDTO resolveOrder(ShoppingCartDTO shoppingCartDTO) {
        return null;
    }
}
