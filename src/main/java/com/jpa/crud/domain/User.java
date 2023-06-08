package com.jpa.crud.domain;

import com.jpa.crud.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter@Setter
@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String password;
    private String token;
    private String roles; // USER ,ADMIN
    private String jwt;
    private LocalDateTime dateTime;


    public User(UserDto userDto) {
        this.id= userDto.getId();
        this.username = userDto.getUsername();
        this.dateTime = userDto.getDateTime();
        this.token = userDto.getToken();
        this.password = userDto.getPassword();
    }

    @Builder
    public User(String username, LocalDateTime dateTime) {
        this.username = username;
        this.dateTime = dateTime;
    }

    public User() {

    }

    public List<String> getRoleList(){

        if(this.roles.length()>0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
}
