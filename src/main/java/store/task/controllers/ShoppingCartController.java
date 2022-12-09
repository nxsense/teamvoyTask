package store.task.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import store.task.dto.GoodsDTO;
import store.task.dto.ShoppingCartDTO;
import store.task.dto.UserDTO;
import store.task.mappers.GoodsMapper;
import store.task.mappers.UserMapper;
import store.task.services.ShoppingCartService;
import store.task.services.UserService;

@RestController
@RequestMapping(name = "/shoppingCart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final GoodsMapper goodsMapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService, UserMapper userMapper, GoodsMapper goodsMapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.goodsMapper = goodsMapper;
    }

    @GetMapping(name = "users-cart")
    public ShoppingCartDTO usersShoppingCart(Authentication authentication) {
        UserDTO userDTO = userService.findUserByEmail(authentication.getName());
        return shoppingCartService.findByUser(userMapper.toEntity(userDTO));
    }

    @PutMapping(value = "addGoods")
    public void addGoodsToCart(Authentication authentication, @RequestBody GoodsDTO goodsDTO, @RequestParam int quantity) {
       shoppingCartService.addGoods(userMapper.toEntity(userService.findUserByEmail(authentication.getName())), goodsMapper.toEntity(goodsDTO), quantity);
    }
}
