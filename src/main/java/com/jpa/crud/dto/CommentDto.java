package com.jpa.crud.dto;

import com.jpa.crud.domain.Board;
import com.jpa.crud.domain.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {


    private Long id;

    private String content;

    private LocalDateTime localDateTime;


    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.localDateTime = LocalDateTime.now();
    }
}
