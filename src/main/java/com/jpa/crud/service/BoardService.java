package com.jpa.crud.service;

import com.jpa.crud.domain.Board;
import com.jpa.crud.dto.BoardAndCommentDto;
import com.jpa.crud.dto.BoardDto;
import com.jpa.crud.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private  final BoardRepository boardRepository;
    //test121

    public Board save(BoardDto  boardDto){

        Board board = new Board(boardDto);


       return boardRepository.save(board);
    }

    public List<Board> findAll(){

        return boardRepository.findAll();
    }


    public Optional<Board> findOne(Long id){

        return  boardRepository.findById(id);

    }

    //수정
    public Board update(Long id , BoardDto boardDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("not find board"));
        log.info("findBoard={}",boardDto);
        board.setTitle(boardDto.getTitle());
        board.setContents(boardDto.getContents());

       return  boardRepository.save(board);

    }

    public void delete (Long id){

        boardRepository.deleteById(id);

    }


    //JPQL 쿼리 사용
    // 게시판 댓글 전체조회

    public List<BoardAndCommentDto> findBoardComment(){
        log.info("BoardService");


        List<BoardAndCommentDto> collect = boardRepository.findBoardAndComment().stream()
                .map(BoardAndCommentDto::new)
                .collect(Collectors.toList());
        
        
        return collect;


    }

    // Native 쿼리 사용 test
    public List<BoardAndCommentDto> findBoardComment2(){
        log.info("BaordService");

        //return boardRepository.findBoardComment();
        List<BoardAndCommentDto> collect = boardRepository.findBoardAndComment2().stream()
                .map(BoardAndCommentDto::new)
                .collect(Collectors.toList());
        return collect;

    }


    public List<BoardDto> findAllBoard(){

        log.info("===========service================");
        List<BoardDto> allBoard = boardRepository.findAllBoard();
        log.info("allBoard={}" , allBoard);


        return allBoard;
    }
}
