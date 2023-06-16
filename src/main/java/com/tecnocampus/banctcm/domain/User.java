package com.tecnocampus.banctcm.domain;

import java.util.UUID;

public class User {
    private String id = UUID.randomUUID().toString();

    private String dni;
    private String name;
    private String address;
    private String birthday;


    public User(){}
    public User(UserDTO userDTO){
        dni=userDTO.getDni();
        name=userDTO.getName();
        address=userDTO.getAddress();
        birthday=userDTO.getBirthday(); // String to Calendar
    }


    public String getId() {
        return id;
    }
    public String getDni() {
        return dni;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getBirthday() {
        return birthday;
    }
}
