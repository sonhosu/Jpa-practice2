package com.jpa.crud.service;


import com.jpa.crud.domain.Board;
import com.jpa.crud.domain.Comment;
import com.jpa.crud.dto.CommentDto;
import com.jpa.crud.repository.BoardRepository;
import com.jpa.crud.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    // 저장
    public CommentDto save(CommentDto commentDto , Long id){

        Board board = boardRepository.findById(id).get();
        commentDto.setBoard(board);

        // Dto -> Entity

        Comment comment = commentRepository.save(new Comment(commentDto));


        //Entity -> Dto
       return  new CommentDto(comment);


    }
    //단건 조회

    public Comment findOne(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(
                        ()-> new IllegalArgumentException("없음")
                );

        return comment;
    }

    //댓글 전체 조회

    public List<Comment> findAll (){

        return commentRepository.findAll();

    }

    // 댓글 수정
    public CommentDto update(CommentDto commentDto  ,Long commentId ){

        Comment findComment = commentRepository.findById(commentId).get();
        findComment.setContent(commentDto.getContent());
        Comment comment = commentRepository.save(findComment);

        return  new CommentDto(comment);


    }

   // 댓글 삭제

    public void delete(Long id){
        commentRepository.deleteById(id);
    }

}
