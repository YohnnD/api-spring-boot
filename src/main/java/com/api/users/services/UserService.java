package com.api.users.services;

import com.api.users.dtos.UserDTO;
import com.api.users.entities.User;
import com.api.users.exceptions.BadRequestException;
import com.api.users.exceptions.response.NotFoundException;
import com.api.users.mappers.UserMapper;
import com.api.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRepository userRepository;

    public UserDTO saveUser(UserDTO userDTO){
        this.userRepository.findByEmail(userDTO.getEmail()).ifPresent(((e)->{
            throw  new BadRequestException("Ya existe un usuario con ese correo");
        }));
        User user = userMapper.toUser(userDTO);
        return  this.userMapper.toUserDTO(userRepository.save(user));
    }

    public ArrayList<UserDTO> getUsers(){
        return (ArrayList<UserDTO>)this.userMapper.userToUsersDTOs(userRepository.findAll());
    }

    public UserDTO getUserById(Long id){
        User user = this.userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("El usuario no se encuentra"));
        return this.userMapper.toUserDTO(user);
    }

    public void deleteUser(Long id){
        this.userRepository.findById(id).orElseThrow(()->new NotFoundException("El usuario no se encuentra"));
        this.userRepository.deleteById(id);
    }

    public UserDTO updatedUser(UserDTO userDTO,Long id){
         User StoredUser =  this.userRepository.findById(id).orElseThrow(()->new NotFoundException("El usuario no se encuentra"));
         this.userMapper.updateUserFromUserDTO(userDTO,StoredUser);
        return this.userMapper.toUserDTO(this.userRepository.save(StoredUser));
    }


}
