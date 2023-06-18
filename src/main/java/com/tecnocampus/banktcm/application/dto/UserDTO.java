package com.tecnocampus.banktcm.application.dto;

import com.tecnocampus.banktcm.domain.User;

public class UserDTO {
    private String key;
    private String id;

    private String dni;
    private String name;
    private String address;
    private String birthday;


    public UserDTO(){}
    public UserDTO(User user) throws Exception{
        id=user.getId();
        key=user.getKey();
    
        dni=user.getDni();
        name=user.getName();
        address=user.getAddress();
        birthday=User.dateFormat.format(user.getBirthday());
    }


    public String getId() {
        return id;
    }
    public String getKey() {
        return key;
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
