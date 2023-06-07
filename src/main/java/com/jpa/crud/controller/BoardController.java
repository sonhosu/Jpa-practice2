package com.jpa.crud.controller;


import com.jpa.crud.domain.Board;
import com.jpa.crud.dto.BoardAndCommentDto;
import com.jpa.crud.dto.BoardCommentDto;
import com.jpa.crud.dto.BoardDto;
import com.jpa.crud.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    HttpHeaders headers = new HttpHeaders();
    //글 목록
    @ResponseBody
    @GetMapping("/boards")
    public ResponseEntity<List<Board>> board(){
        List<Board> boardList = boardService.findAll();

        if(boardList.isEmpty()){
            return null ;
        }
        return ResponseEntity.ok()
                .headers(headers)
                .body(boardList);
    }

    //글 상세
    @ResponseBody
    @GetMapping("/board/detail/{boardId}")
    public Optional<Board> boardDetail(@PathVariable Long boardId , Model model){
        log.info("boardId={}" , boardId);

        return  boardService.findOne(boardId);

    }

    // 글생성
    @ResponseBody
    @PostMapping("/board/write")
    public ResponseEntity<Board> boardWrite(@RequestBody BoardDto boardDto){
        log.info("BoardDto={}" , boardDto);

        HttpHeaders headers = new HttpHeaders();
        Board savedBoard = boardService.save(boardDto);

        log.info("savedBoard={}" , savedBoard);
        return ResponseEntity.ok()
                .headers(headers)
                .body(savedBoard);

    }

    // 수정
    @ResponseBody
    @PostMapping("/board/update/{boardId}")
    public Board boardUpdate(@PathVariable Long boardId , @RequestBody BoardDto boardDto){
        log.info("BoardDto={}" , boardDto);


        return boardService.update(boardId , boardDto);

    }

    //삭제
    @ResponseBody
    @DeleteMapping("/board/delete/{boardId}")
    public void boardDelete(@PathVariable Long boardId){
        log.info("boardId={}",boardId);

        boardService.delete(boardId);

    }

    @ResponseBody
    @GetMapping("/boardComments")
    public List<BoardAndCommentDto> findAllBoardComment(){
        log.info("BoardController");
        List<BoardAndCommentDto> boardComment = boardService.findBoardComment();

        return boardComment;
    }

    @ResponseBody
    @GetMapping("/boardComments2")
    public List<BoardAndCommentDto> findAllBoardComment2(){
        log.info("BoardController");
        List<BoardAndCommentDto> boardComment = boardService.findBoardComment2();

        return boardComment;
    }



    @ResponseBody
    @GetMapping("/boardss")
    public List<BoardDto> findAllBoard(){
        log.info("=====================");
        List<BoardDto> boardList = boardService.findAllBoard();

        return boardList;
    }

}
