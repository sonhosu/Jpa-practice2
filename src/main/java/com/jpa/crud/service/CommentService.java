package com.jpa.crud.service;


import com.jpa.crud.domain.Comment;
import com.jpa.crud.dto.CommentDto;
import com.jpa.crud.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;


    public Comment save(CommentDto commentDto){

        Comment comment = new Comment(commentDto);

        return commentRepository.save(comment);

    }

    public Comment findOne(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(
                        ()-> new IllegalArgumentException("없음")
                );

        return comment;
    }

    public List<Comment> findAll (){

        return commentRepository.findAll();

    }

    public Comment update(Long id , CommentDto commentDto){
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없음")
        );
        comment.setContent(commentDto.getContent());
        comment.setDateTime(LocalDateTime.now());

        return  commentRepository.save(comment);

    }

    public void delete(Long id){
        commentRepository.deleteById(id);
    }

}
