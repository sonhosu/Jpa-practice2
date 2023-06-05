package com.jpa.crud.repository;

import com.jpa.crud.domain.Board;
import com.jpa.crud.dto.BoardAndCommentDto;
import com.jpa.crud.dto.BoardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long>, CustomBoardRepository {
    @Override
    List<Board> findAll();

    @Query(nativeQuery = true,
    value = "select * from Board")
    List<BoardDto> findAllBoard2();

    @Query( "select distinct b from Board b left join Comment c on c.board.id = b.id")
    List<Board> findBoardAndComment();



}
