package com.jpa.crud.repository;


import com.jpa.crud.domain.Board;
import com.jpa.crud.dto.BoardAndCommentDto;
import com.jpa.crud.dto.BoardCommentDto;
import com.jpa.crud.dto.BoardDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomBoardRepository {

    public List<BoardCommentDto> findBoardComment();


    public List<BoardDto> findAllBoard();


}
