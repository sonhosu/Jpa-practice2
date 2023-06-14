package com.jpa.crud.repository;


import com.jpa.crud.dto.BoardCommentDto;
import com.jpa.crud.dto.BoardDto;
import com.jpa.crud.dto.SearchDto;

import java.util.List;

public interface CustomBoardRepository {

    public List<BoardCommentDto> findBoardComment();


    public List<BoardDto> findAllBoard();


    List<BoardDto> findSearchBoard(SearchDto searchDto);
}
