package store.task.services;

import store.task.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO save(UserDTO dto);
    UserDTO findUserByEmail(String email);
    List<UserDTO> findAllUsers();
}
