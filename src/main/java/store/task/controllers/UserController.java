package store.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.task.dto.OrderDTO;
import store.task.dto.UserDTO;
import store.task.entities.User;
import store.task.mappers.UserMapper;
import store.task.services.OrdersService;
import store.task.services.UserService;

@RestController
@RequestMapping(name = "api/user")
public class UserController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserDetailsService userDetailsService, UserMapper userMapper) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.userMapper = userMapper;
    }

    @PutMapping(value = "register")
    public ResponseEntity<UserDTO> registration(@RequestBody User user){
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if(userDetailsService.loadUserByUsername(user.getEmail()) == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        userService.save(userMapper.toDto(user));
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.ACCEPTED);
    }
}
