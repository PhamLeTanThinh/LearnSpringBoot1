package com.thinplt.springboot1.mapper;

import com.thinplt.springboot1.dto.UserDTO;
import com.thinplt.springboot1.entity.User;

public class UserMapper {

//    Conver User to UserDTO
    public static UserDTO mapToUserDTO(User user){
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDTO;
    }

    //    Conver UserDTO to User
    public static User mapToUser(UserDTO userDTO){
        User user = new User(
                userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail()
        );
        return user;
    }
}
