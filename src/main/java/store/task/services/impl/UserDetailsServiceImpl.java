package store.task.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import store.task.entities.User;
import store.task.mappers.UserMapper;
import store.task.services.UserService;
import org.springframework.security.core.userdetails.User.UserBuilder;

import java.util.stream.Collectors;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final UserMapper mapper;

    @Autowired
    public UserDetailsServiceImpl(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = mapper.toEntity(userService.findUserByEmail(email));
        UserBuilder builder = withUsername(email);
        builder.password(user.getPassword());
        builder.roles(user.getRoles().toString());
        return builder.build();
    }
}
