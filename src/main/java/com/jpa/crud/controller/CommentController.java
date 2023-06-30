package com.jpa.crud.controller;

import com.jpa.crud.domain.Comment;
import com.jpa.crud.dto.CommentDto;
import com.jpa.crud.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    //댓글 생성

    /*@PostMapping("/api/v1/user/board/{boardId}/comment/write")
    public CommentDto create(@RequestBody CommentDto inCommentDto , @PathVariable Long boardId){
        log.info("commentDto={}" , inCommentDto ,"boardId={}" ,boardId);

        CommentDto commentDto = commentService.save(inCommentDto,boardId);

        return commentDto;

    }*/

    @PostMapping("/api/v1/user/comment/write")
    public CommentDto create(@RequestBody CommentDto inCommentDto ){
        //log.info("inCommentDto.getBoard().getId()={}" , inCommentDto.getBoard().getId() );

        CommentDto commentDto = commentService.save(inCommentDto,inCommentDto.getBoard().getId());

        return commentDto;

    }


    // 댓글 수정
    /*@PutMapping("/api/v1/user/comment/{commentId}/update")
    public ResponseEntity<CommentDto> update (@RequestBody CommentDto commentDto , @PathVariable Long commentId){
        CommentDto update = commentService.update(commentDto, commentId);

        return new ResponseEntity<>(update,HttpStatus.OK);
    }*/

    @PutMapping("/api/v1/user/comment/update")
    public ResponseEntity<CommentDto> update (@RequestBody CommentDto commentDto ){
        CommentDto update = commentService.update(commentDto, commentDto.getId());

        return new ResponseEntity<>(update,HttpStatus.OK);       //qweqweqw
    }


    // 댓글 삭제

    @DeleteMapping("/api/v1/user/comment/delete")
    public ResponseEntity<CommentDto> delete ( @RequestBody CommentDto commentDto){
        commentService.delete(commentDto.getId());

        return new ResponseEntity<>(HttpStatus.OK);


    }

    @ResponseBody
    @GetMapping("/api/v1/user/comments")
    public List<CommentDto> findAll(){

        return commentService.findAll();
    }



}
