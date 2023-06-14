package com.jpa.crud.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardCommentDto {

    private String title;
    private String bContent;
    private String cContent;


    public BoardCommentDto(String title, String bContent, String cContent) {
        this.title = title;
        this.bContent = bContent;
        this.cContent = cContent;
    }
}
