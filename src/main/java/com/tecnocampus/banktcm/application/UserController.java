package com.tecnocampus.banktcm.application;

import org.springframework.stereotype.Controller;

import com.tecnocampus.banktcm.application.dto.UserDTO;
import com.tecnocampus.banktcm.domain.User;
import com.tecnocampus.banktcm.persistence.UserRepository;

@Controller
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDTO createUser(UserDTO userDTO) throws Exception {
        User user = new User(userDTO);
        userRepository.save(user);

        return new UserDTO(user);
    }

    public UserDTO getUser(String userId) throws Exception {
        return new UserDTO(userRepository.findById(userId).get());
    }

    public void removeUser(String userId) throws Exception {
        userRepository.deleteById(userId);
    }


}
