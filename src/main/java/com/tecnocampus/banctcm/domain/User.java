package com.tecnocampus.banctcm.domain;

import java.util.UUID;

public class User {
    private String id = UUID.randomUUID().toString();

    private String name;
    private String address;
    private String dni;


    public User(){}
    public User(UserDTO userDTO){
        
    }

}
