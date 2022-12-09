package store.task.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.task.dto.OrderDTO;
import store.task.dto.ShoppingCartDTO;
import store.task.mappers.UserMapper;
import store.task.services.OrdersService;
import store.task.services.ShoppingCartService;
import store.task.services.UserService;

import java.util.List;

@RestController
@RequestMapping(name = "/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final UserMapper userMapper;

    public OrdersController(OrdersService ordersService, ShoppingCartService shoppingCartService, UserService userService, UserMapper userMapper) {
        this.ordersService = ordersService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(value="/allUsersOrders")
    public List<OrderDTO> allOrders(Authentication authentication){
        return ordersService.findAllByUserId(userService.findUserByEmail(authentication.getName()).getId());
    }

    @PutMapping(value = "/resolveOrder")
    public OrderDTO resolveOrder(Authentication authentication){
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.findByUser(userMapper.toEntity(userService.findUserByEmail(authentication.getName())));
        return ordersService.resolveOrder(shoppingCartDTO);
    }
}
