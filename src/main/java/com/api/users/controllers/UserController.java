package com.api.users.controllers;


import com.api.users.dtos.UserDTO;
import com.api.users.response.ResponseHandler;
import com.api.users.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController

@RequestMapping("/api/v1/users")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> getUsers (){
        return ResponseHandler.generate("success data", HttpStatus.OK,this.userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<Object> storeUsers(@Validated @RequestBody UserDTO userDTO){
        return ResponseHandler.generate("created data",HttpStatus.CREATED,this.userService.saveUser(userDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable Long id){
        return ResponseHandler.generate("show data",HttpStatus.OK,this.userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
      this.userService.deleteUser(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody UserDTO userDTO ,@PathVariable Long id){
        return ResponseHandler.generate("update data",HttpStatus.OK,this.userService.updatedUser(userDTO,id));
    }

}
