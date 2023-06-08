package com.jpa.crud.dto;

import com.jpa.crud.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDto {

    private Long id;

    private String title;

    private String contents;

    private LocalDateTime datetime;

    private String username;

    private User user;
}
