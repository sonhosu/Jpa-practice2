package com.jpa.crud.repository;


import com.jpa.crud.domain.Board;
import com.jpa.crud.dto.BoardCommentDto;
import com.jpa.crud.dto.BoardDto;

import java.util.List;

public interface CustomBoardRepository {

    public List<BoardCommentDto> findBoardComment();


    public List<BoardDto> findAllBoard();


}
