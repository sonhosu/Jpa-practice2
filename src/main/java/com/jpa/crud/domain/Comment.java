package com.jpa.crud.domain;

import com.jpa.crud.dto.CommentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor

public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @Column
    private String content;
    @Column
    private LocalDateTime dateTime;

    @Column
    private LocalDateTime updateTime;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;




    public Comment(CommentDto commentDto){
        this.id = commentDto.getId();
        this.content = commentDto.getContent();
        this.dateTime = commentDto.getLocalDateTime();
        this.board = commentDto.getBoard();
        this.updateTime = commentDto.getUpdateTime();
    }


}
