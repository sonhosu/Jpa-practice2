package com.jpa.crud.dto;

import com.jpa.crud.domain.Board;
import com.jpa.crud.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CommentDto {


    private Long id;

    private String content;

    private LocalDateTime writeTime;

    private LocalDateTime updateTime;

    private Board board;



    @Builder
    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.writeTime = comment.getDateTime();
    }

    public Comment toEntity (){
        return Comment.builder()
                .content(content)
                .dateTime(writeTime)
                .updateTime(updateTime)
                .board(board)
                .build();




    }



}
