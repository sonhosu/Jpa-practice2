package com.jpa.crud.service;

import com.jpa.crud.domain.Board;
import com.jpa.crud.dto.BoardAndCommentDto;
import com.jpa.crud.dto.BoardDto;
import com.jpa.crud.repository.BoardRepository;
import com.jpa.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private  final BoardRepository boardRepository;
    private  final UserRepository userRepository;
    //test121

    public BoardDto save(BoardDto  boardDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String username = userDetails.getUsername();

        boardDto.setUsername(username);
        Board board = new Board(boardDto);

        Board savedBoard = boardRepository.save(board);

        BoardDto boardDto1 = new BoardDto();
        boardDto1.setId(savedBoard.getId());
        boardDto1.setTitle(savedBoard.getTitle());
        boardDto1.setContents(savedBoard.getContents());
        boardDto1.setUsername(savedBoard.getUsername());
        boardDto1.setDatetime(savedBoard.getDateTime());


        return boardDto1;
    }

    public List<Board> findAll(){

        return boardRepository.findAll();
    }


    public BoardDto findOne(Long id){
        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = new BoardDto();

        boardDto.setId(board.getId());
        boardDto.setTitle(board.getTitle());
        boardDto.setContents(board.getContents());
        boardDto.setUsername(board.getUsername());
        boardDto.setDatetime(board.getDateTime());

        return boardDto ;

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
