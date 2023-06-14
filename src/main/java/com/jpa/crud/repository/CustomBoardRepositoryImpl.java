package com.jpa.crud.repository;

import com.jpa.crud.dto.BoardCommentDto;
import com.jpa.crud.dto.BoardDto;
import com.jpa.crud.dto.SearchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository

public class CustomBoardRepositoryImpl implements CustomBoardRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<BoardCommentDto> findBoardComment() {
        StringBuffer sql = new StringBuffer();
        List<BoardCommentDto> boardCommentDtoList = new ArrayList<>();

        sql.append("SELECT  b.title ,b.contents , c.content ");
        sql.append("FROM Board b ");
        sql.append("LEFT JOIN Comment c ");
        sql.append("on b.id = c.id");
       // Query query = em.createQuery("select b.id ,b.title ,b.contents ,c.content from Board b left join Comment c on b.id = c.id");

        List<BoardCommentDto> resultList = em.createQuery(sql.toString(), BoardCommentDto.class)
                .getResultList();
        log.info("resultList={}",resultList.size());


//        for (Object[] row : resultList){
//            BoardCommentDto boardCommentDto = new BoardCommentDto();
//            boardCommentDto.setTitle((String)row[0]);
//            boardCommentDto.setCContent((String)row[1]);
//            boardCommentDto.setBContent((String)row[2]);
//
//
//            boardCommentDtoList.add(boardCommentDto);
//        }

        return resultList;


    }

    @Override
    public List<BoardDto> findAllBoard() {
        
        StringBuffer sql = new StringBuffer();

        sql.append("SELECT b.id , b.title , b.contents ");
        sql.append("FROM Board b ");

        List<Object[]> resultList = em.createQuery(sql.toString(), Object[].class)
                .getResultList();

        return resultList.stream().map(
                result -> new BoardDto(
                        (Long)   result[0],
                        (String) result[1],
                        (String) result[2]
                )
        ).collect(Collectors.toList());

        /*for(Object[] row : resultList){
            BoardDto boardDto = new BoardDto();
            boardDto.setId((Long)row[0]);
            boardDto.setTitle((String) row[1]);
            boardDto.setContents((String) row[2]);

            boardDtoList.add(boardDto);

        }


        log.info("resultList={}" , resultList);



        return boardDtoList ;*/
    }



    @Override
    public List<BoardDto> findSearchBoard(SearchDto searchDto) {
        StringBuffer sql = new StringBuffer();

        sql.append("SELECT b.id , b.title , b.contents ");
        sql.append("FROM Board b ");
        sql.append("WHERE 1=1 ");

        if(!searchDto.getTitle().equals("") && searchDto.getTitle() != null){

            sql.append("and b.title like CONCAT('%', :keyword , '%')");
        }

        TypedQuery<Object[]> query = em.createQuery(sql.toString(), Object[].class);
        query.setParameter("keyword",searchDto.getTitle());


        return query.getResultList().stream().map(
                result -> new BoardDto(
                        (Long)   result[0],
                        (String) result[1],
                        (String) result[2]
                )
        ).collect(Collectors.toList());
       
    }

}


