package com.thinplt.springboot1.service;

import com.thinplt.springboot1.dto.UserDTO;
import com.thinplt.springboot1.entity.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUser();
    UserDTO updateUser(UserDTO user);
    void deleteUser(Long id);
}
