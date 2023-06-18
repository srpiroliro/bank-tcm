package com.tecnocampus.banktcm.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tecnocampus.banktcm.application.UserController;
import com.tecnocampus.banktcm.application.dto.UserDTO;


@RestController
public class UserRestController {
    private UserController userController;

    public UserRestController(UserController userController){
        this.userController=userController;
    }



    @PostMapping("/users")
    public UserDTO createUser(@RequestBody UserDTO userDTO) throws Exception {
        return userController.createUser(userDTO);
    }

    @GetMapping("/users/{userId}")
    public UserDTO getUser(@PathVariable String userId) throws Exception {
        return userController.getUser(userId);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable String userId) throws Exception {
        userController.deleteUser(userId);
    }
}
