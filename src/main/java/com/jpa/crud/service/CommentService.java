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
import java.util.stream.Collectors;

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
        Comment entity = commentDto.toEntity();

        Comment comment = commentRepository.save(entity);

        CommentDto commentDto1 = new CommentDto();
        commentDto1.setId(comment.getId());
        commentDto1.setBoard(comment.getBoard());
        commentDto1.setContent(comment.getContent());
        commentDto1.setWriteTime(comment.getDateTime());
        commentDto1.setUpdateTime(LocalDateTime.now());

       return  commentDto1;


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

    public List<CommentDto> findAll (){

        return commentRepository.findAll().stream().
                map(CommentDto::new)
                .collect(Collectors.toList());

    }

    // 댓글 수정
    public CommentDto update(CommentDto commentDto  ,Long commentId ){

        Comment findComment = commentRepository.findById(commentId).get();
        findComment.setContent(commentDto.getContent());
        Comment comment = commentRepository.save(findComment);

        CommentDto OutCommentDto = new CommentDto();

        OutCommentDto.setId(comment.getId());
        OutCommentDto.setContent(comment.getContent());
        OutCommentDto.setBoard(comment.getBoard());
        OutCommentDto.setWriteTime(comment.getDateTime());
        OutCommentDto.setUpdateTime(comment.getUpdateTime());

        return  OutCommentDto;


    }

   // 댓글 삭제

    public void delete(Long id){
        commentRepository.deleteById(id);
    }

}
