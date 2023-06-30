package com.jpa.crud.controller;


import com.jpa.crud.domain.Board;
import com.jpa.crud.dto.BoardAndCommentDto;
import com.jpa.crud.dto.BoardDto;
import com.jpa.crud.dto.SearchDto;
import com.jpa.crud.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    HttpHeaders headers = new HttpHeaders();


    @ResponseBody
    @GetMapping("/test1")
    public String test1(){
        return "hello";
    }
    //글 목록
    @ResponseBody
    @GetMapping("/api/v1/user/boards")
    public ResponseEntity<List<Board>> board(){
        List<Board> boardList = boardService.findAll();

        if(boardList.isEmpty()){
            return null ;
        }
        return ResponseEntity.ok()
                .headers(headers)
                .body(boardList);
    }

    //글 상세1
    @ResponseBody
    @GetMapping("/api/v1/user/board/detail")
    public BoardDto boardDetail(@RequestBody RequestDto requestDto){
        log.info("boardId={}" , requestDto.getId());
        BoardDto findOne = boardService.findOne(requestDto.getId());
        return  findOne;

    }

    // 글생성
    @ResponseBody
    @PostMapping("/api/v1/user/board/write")
    public BoardDto boardWrite(@RequestBody BoardDto boardDto){
        log.info("BoardDto={}" , boardDto);


       BoardDto boardDto1 = boardService.save(boardDto);


        return boardDto1;

    }

    // 수정  pathVariable 방식
    @ResponseBody
    @PutMapping("/api/v1/user/board/update")
    public BoardDto boardUpdate(@RequestBody BoardDto boardDto){
        log.info("BoardDto={}" , boardDto);

        BoardDto update = boardService.update(boardDto.getId(), boardDto);


        return update;

    }

    // 수정 none pathVariable 방식
    @ResponseBody
    @PostMapping("/api/v2/user/board/update")
    public BoardDto boardUpdate2(@RequestBody BoardDto boardDto){
        log.info("Board");

        BoardDto update = boardService.update2(boardDto);


        return update;
    }


    //삭제
    @ResponseBody
    @DeleteMapping("/api/v1/user/board/delete")
    public String boardDelete(@RequestBody BoardDto boardDto){
        log.info("boardDto={}",boardDto);

        boardService.delete(boardDto.getId());

        return"삭제완료";

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

    @ResponseBody
    @GetMapping("/board/search")
    public List<BoardDto> findSearchBoard(@RequestBody SearchDto searchDto){
        log.info("searchDto={}",searchDto);

        List<BoardDto> searchBoard = boardService.findSearchBoard(searchDto);

        return searchBoard;
    }


}
