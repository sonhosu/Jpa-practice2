package com.jpa.crud.dto;

import com.jpa.crud.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String token;
    private LocalDateTime dateTime;
    private String roles; // USER ,ADMIN
    private String jwt;

    public UserDto(User user) {
        this.id = user.getId();
        this.username= user.getUsername();
        this.dateTime = LocalDateTime.now();
    }

    public User toEntity (){
        return User.builder()
                .username(username)
                .dateTime(LocalDateTime.now())
                .build();
    }
}
