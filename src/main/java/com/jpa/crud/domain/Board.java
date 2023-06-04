package com.jpa.crud.domain;


import com.jpa.crud.dto.BoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.websocket.server.ServerEndpoint;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Board {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    @Column
    private String title;
    @Column
    private String contents;
    @Column
    private LocalDateTime dateTime;

    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "board")
    private List<Comment> comment = new ArrayList<>();

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member user;


    public Board(BoardDto boardDto) {
        this.title = boardDto.getTitle();
        this.contents = boardDto.getContents();
        this.dateTime = LocalDateTime.now();
        this.user = boardDto.getUser();
    }

    public Board(Long id ,String title, String contents){

        this.id =id;
        this.title =title;
        this.contents =contents;
    }


}
