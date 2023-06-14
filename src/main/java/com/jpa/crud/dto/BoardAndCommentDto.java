package com.jpa.crud.dto;

import com.jpa.crud.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class BoardAndCommentDto {

    private Long id;

    //werwerwerwerwerwer

    private String title;

    private String contents;

    private LocalDateTime dateTime;

    private List<CommentDto> commentDto;

    public BoardAndCommentDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.dateTime = board.getDateTime();
        this.contents = board.getContents();
        this.commentDto = board.getComment().stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }

}
