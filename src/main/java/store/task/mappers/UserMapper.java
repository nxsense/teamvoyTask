package store.task.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.task.dto.UserDTO;
import store.task.entities.User;

@Component
public class UserMapper extends AbstractMapper<User, UserDTO>{
    @Autowired
    UserMapper(Class<User> entityClass, Class<UserDTO> dtoClass) {
        super(entityClass, dtoClass);
    }
}

