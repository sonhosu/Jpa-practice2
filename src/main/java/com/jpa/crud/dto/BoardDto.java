package com.jpa.crud.dto;

import com.jpa.crud.domain.Board;
import com.jpa.crud.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long id;

    private String title;

    private String contents;

    private LocalDateTime datetime;

    private LocalDateTime updateTime;

    private String username;

    private User user;


    @Builder
    public BoardDto (Board board){
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.username = board.getUsername();
        this.id = board.getId();
    }

    public BoardDto(Long id, String title, String contents) {
        this.id = id;
        this.title= title;
        this.contents = contents;

    }

    public Board toEntity (){
        return Board.builder()
                .id(id)
                .title(title)
                .contents(contents)
                .dateTime(datetime)
                .username(username)
                .build();
    }
}
