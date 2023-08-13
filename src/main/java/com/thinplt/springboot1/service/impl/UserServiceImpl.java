package com.thinplt.springboot1.service.impl;

import com.thinplt.springboot1.dto.UserDTO;
import com.thinplt.springboot1.entity.User;
import com.thinplt.springboot1.exception.EmailAlreadyExistException;
import com.thinplt.springboot1.exception.ResourceNotFoundException;
import com.thinplt.springboot1.mapper.UserMapper;
import com.thinplt.springboot1.repository.UserRepository;
import com.thinplt.springboot1.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
//    Convert UserDTO into User JPA Entity
//      User user = UserMapper.mapToUser(userDTO);

        Optional<User> optionalUser = userRepository.findByEmail(userDTO.getEmail());

        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistException("Email Already Exist for User");
        }

        User user = modelMapper.map(userDTO, User.class);

        User savedUser = userRepository.save(user);

        // Convert UserJPA entity to UserDTO
//        UserDTO savedUserDTO = UserMapper.mapToUserDTO(savedUser);
        UserDTO savedUserDTO = modelMapper.map(savedUser, UserDTO.class);
        return savedUserDTO;
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );
//        User user = optionalUser.get();
//        return UserMapper.mapToUserDTO(user);
        return modelMapper.map(user, UserDTO.class);
    }


    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(UserMapper::mapToUserDTO).collect(Collectors.toList());
        return users.stream().map((user) -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());

    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDTO.getId())
        );
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());

        User updatedUser = userRepository.save(existingUser);

//        UserDTO convertedUser = UserMapper.mapToUserDTO(updatedUser);
        UserDTO convertedUser = modelMapper.map(updatedUser, UserDTO.class);
        return convertedUser;
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );
        userRepository.deleteById(id);
    }
}
