package store.task.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.task.dto.OrderDTO;
import store.task.dto.ShoppingCartDTO;
import store.task.entities.Order;
import store.task.entities.ShoppingCart;
import store.task.mappers.OrderMapper;
import store.task.mappers.ShoppingCartMapper;
import store.task.repositories.OrderRepository;
import store.task.repositories.ShoppingCartRepository;
import store.task.repositories.UserRepository;
import store.task.services.OrdersService;
import store.task.services.ShoppingCartService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderMapper mapper;
    private final ShoppingCartMapper shoppingCartMapper;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public OrdersServiceImpl(UserRepository userRepository, OrderRepository orderRepository, ShoppingCartRepository shoppingCartRepository, OrderMapper mapper, ShoppingCartMapper shoppingCartMapper, ShoppingCartService shoppingCartService) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.mapper = mapper;
        this.shoppingCartMapper = shoppingCartMapper;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public List<OrderDTO> findAllByUserId(int id) {
        return orderRepository.findAllByUser(userRepository.getReferenceById(id)).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDTO resolveOrder(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart =  shoppingCartRepository.findById(shoppingCartMapper.toEntity(shoppingCartDTO).getId()).get();
        Order order = new Order();
        order.setGoods(shoppingCart.getGoods());
        order.setUser(shoppingCart.getUser());
        order.setCreatingTime(System.currentTimeMillis());
        order.setCompleted(false);
        order.setPaid(false);
        shoppingCartService.clear(shoppingCart.getId());
        return mapper.toDto(order);
    }

    @Override
    public List<OrderDTO> findAllOrders() {
        return orderRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(OrderDTO orderDTO) {
        orderRepository.delete(mapper.toEntity(orderDTO));
    }
}
