package com.tecnocampus.banktcm.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import com.tecnocampus.banktcm.application.dto.UserDTO;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;
import lombok.Getter;


@NoArgsConstructor
@Getter
@Entity
public class User {
    public static SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    private static final int MAX_ACCOUNTS=Integer.MAX_VALUE;

    @Id
    private String id = UUID.randomUUID().toString();

    private String key = UUID.randomUUID().toString();

    private String dni;
    private String name;
    private String address;
    private Calendar birthday=Calendar.getInstance();


    // CHECK: here?
    @OneToMany // @ManyToOne
    private List<Account> accounts=new ArrayList<Account>();

    public User(UserDTO userDTO) throws Exception{
        dni=userDTO.getDni();
        name=userDTO.getName();
        address=userDTO.getAddress();

        parseBirthday(userDTO.getBirthday());
    }

    private void parseBirthday(String date) throws Exception {
        try {
            birthday.setTime(dateFormat.parse(date));
        } catch (ParseException e) {
            throw new Exception("Birthday must be in following format: dd/mm/yyyy");
        }
    }

    public void addAccount(Account account) throws Exception {
        if(accounts.size()>=MAX_ACCOUNTS) throw new Exception("Account limit reached!");

        accounts.add(account);
    }

    public void removeAccount(String accountId) throws Exception{
        accounts.removeIf(a->a.getId().equals(accountId));
    }
    
    public boolean verifyKey(String keyToCheck){
        return key.equals(keyToCheck);
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
    public Calendar getBirthday() {
        return birthday;
    }
}
