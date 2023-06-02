package com.jpa.crud.controller;

import com.jpa.crud.domain.Comment;
import com.jpa.crud.dto.CommentDto;
import com.jpa.crud.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/write")
    public CommentDto create(@RequestBody CommentDto inCommentDto){
        log.info("commentDto={}" , inCommentDto);
        

        Comment savedComment = commentService.save(inCommentDto);
        CommentDto outCommentDto = new CommentDto(savedComment);

        return outCommentDto;

    }



}
