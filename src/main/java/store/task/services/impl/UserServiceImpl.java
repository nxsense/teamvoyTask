package store.task.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.task.dto.UserDTO;
import store.task.entities.User;
import store.task.mappers.UserMapper;
import store.task.repositories.UserRepository;
import store.task.services.ShoppingCartService;
import store.task.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final ShoppingCartService shoppingCartService;
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(PasswordEncoder encoder, UserRepository userRepository, ShoppingCartService shoppingCartService, UserMapper userMapper) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.shoppingCartService = shoppingCartService;
        this.mapper = userMapper;
    }

    @Override
    public UserDTO save(UserDTO dto) {
        User user = mapper.toEntity(dto);
        user.setPassword(encoder.encode(user.getPassword()));
        shoppingCartService.initializeShoppingCart(user);
        return mapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        return mapper.toDto(userRepository.findByEmail(email));
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
